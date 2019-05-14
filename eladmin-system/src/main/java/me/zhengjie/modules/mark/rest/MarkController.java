package me.zhengjie.modules.mark.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.modules.mark.service.MarkService;
import me.zhengjie.modules.mark.service.dto.MarkDTO;
import me.zhengjie.modules.mark.service.query.MarkQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-14
*/
@RestController
@RequestMapping("api")
public class MarkController {

    @Autowired
    private MarkService markService;

    @Autowired
    private MarkQueryService markQueryService;

    private static final String ENTITY_NAME = "mark";

    @Log("查询Mark")
    @GetMapping(value = "/mark")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getMarks(MarkDTO resources, Pageable pageable){
        return new ResponseEntity(markQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Mark")
    @PostMapping(value = "/mark")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Mark resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(markService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Mark")
    @PutMapping(value = "/mark")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Mark resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        markService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Mark")
    @DeleteMapping(value = "/mark/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String id){
        markService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}