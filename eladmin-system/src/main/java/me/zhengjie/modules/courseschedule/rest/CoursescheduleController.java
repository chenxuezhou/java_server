package me.zhengjie.modules.courseschedule.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.courseschedule.domain.Courseschedule;
import me.zhengjie.modules.courseschedule.service.CoursescheduleService;
import me.zhengjie.modules.courseschedule.service.dto.CoursescheduleDTO;
import me.zhengjie.modules.courseschedule.service.query.CoursescheduleQueryService;
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
public class CoursescheduleController {

    @Autowired
    private CoursescheduleService coursescheduleService;

    @Autowired
    private CoursescheduleQueryService coursescheduleQueryService;

    private static final String ENTITY_NAME = "courseschedule";

    @Log("查询Courseschedule")
    @GetMapping(value = "/courseschedule")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCourseschedules(CoursescheduleDTO resources, Pageable pageable){
        return new ResponseEntity(coursescheduleQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Courseschedule")
    @PostMapping(value = "/courseschedule")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Courseschedule resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(coursescheduleService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Courseschedule")
    @PutMapping(value = "/courseschedule")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Courseschedule resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        coursescheduleService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Courseschedule")
    @DeleteMapping(value = "/courseschedule/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String id){
        coursescheduleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}