package me.zhengjie.modules.sub.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.sub.domain.Sub;
import me.zhengjie.modules.sub.service.SubService;
import me.zhengjie.modules.sub.service.dto.SubDTO;
import me.zhengjie.modules.sub.service.query.SubQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-27
*/
@RestController
@RequestMapping("api")
public class SubController {

    @Autowired
    private SubService subService;

    @Autowired
    private SubQueryService subQueryService;

    private static final String ENTITY_NAME = "sub";

    @Log("查询Sub")
    @GetMapping(value = "/sub")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getSubs(SubDTO resources, Pageable pageable){
        return new ResponseEntity(subQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Sub")
    @PostMapping(value = "/sub")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Sub resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(subService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Sub")
    @PutMapping(value = "/sub")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Sub resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        subService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Sub")
    @DeleteMapping(value = "/sub/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        subService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}