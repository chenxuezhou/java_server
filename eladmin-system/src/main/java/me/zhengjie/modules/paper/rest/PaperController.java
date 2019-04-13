package me.zhengjie.modules.paper.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.paper.domain.Paper;
import me.zhengjie.modules.paper.service.PaperService;
import me.zhengjie.modules.paper.service.dto.PaperDTO;
import me.zhengjie.modules.paper.service.query.PaperQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-03-12
*/
@RestController
@RequestMapping("api")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperQueryService paperQueryService;

    private static final String ENTITY_NAME = "paper";

    @Log("查询Paper")
    @GetMapping(value = "/paper")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPapers(PaperDTO resources, Pageable pageable){
        return new ResponseEntity(paperQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Paper")
    @PostMapping(value = "/paper")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Paper resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(paperService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Paper")
    @PutMapping(value = "/paper")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Paper resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        paperService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Paper")
    @DeleteMapping(value = "/paper/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        paperService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}