package me.zhengjie.modules.courselist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.courselist.domain.Courselist;
import me.zhengjie.modules.courselist.service.CourselistService;
import me.zhengjie.modules.courselist.service.dto.CourselistDTO;
import me.zhengjie.modules.courselist.service.query.CourselistQueryService;
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
public class CourselistController {

    @Autowired
    private CourselistService courselistService;

    @Autowired
    private CourselistQueryService courselistQueryService;

    private static final String ENTITY_NAME = "courselist";

    @Log("查询Courselist")
    @GetMapping(value = "/courselist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCourselists(CourselistDTO resources, Pageable pageable){
        return new ResponseEntity(courselistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Courselist")
    @PostMapping(value = "/courselist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Courselist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(courselistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Courselist")
    @PutMapping(value = "/courselist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Courselist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        courselistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Courselist")
    @DeleteMapping(value = "/courselist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        courselistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}