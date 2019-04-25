package me.zhengjie.modules.classdata.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.classdata.domain.Classdata;
import me.zhengjie.modules.classdata.service.ClassdataService;
import me.zhengjie.modules.classdata.service.dto.ClassdataDTO;
import me.zhengjie.modules.classdata.service.query.ClassdataQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-24
*/
@RestController
@RequestMapping("api")
public class ClassdataController {

    @Autowired
    private ClassdataService classdataService;

    @Autowired
    private ClassdataQueryService classdataQueryService;

    private static final String ENTITY_NAME = "classdata";

    @Log("查询Classdata")
    @GetMapping(value = "/classdata")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getClassdatas(ClassdataDTO resources, Pageable pageable){
        return new ResponseEntity(classdataQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Classdata")
    @PostMapping(value = "/classdata")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Classdata resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(classdataService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Classdata")
    @PutMapping(value = "/classdata")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Classdata resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        classdataService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Classdata")
    @DeleteMapping(value = "/classdata/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        classdataService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}