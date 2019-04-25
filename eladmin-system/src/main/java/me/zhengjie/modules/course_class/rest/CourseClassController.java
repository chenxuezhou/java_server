package me.zhengjie.modules.course_class.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.course_class.domain.CourseClass;
import me.zhengjie.modules.course_class.service.CourseClassService;
import me.zhengjie.modules.course_class.service.dto.CourseClassDTO;
import me.zhengjie.modules.course_class.service.query.CourseClassQueryService;
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
public class CourseClassController {

    @Autowired
    private CourseClassService courseClassService;

    @Autowired
    private CourseClassQueryService courseClassQueryService;

    private static final String ENTITY_NAME = "courseClass";

    @Log("查询CourseClass")
    @GetMapping(value = "/courseClass")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCourseClasss(CourseClassDTO resources, Pageable pageable){
        return new ResponseEntity(courseClassQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增CourseClass")
    @PostMapping(value = "/courseClass")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody CourseClass resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(courseClassService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改CourseClass")
    @PutMapping(value = "/courseClass")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody CourseClass resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        courseClassService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除CourseClass")
    @DeleteMapping(value = "/courseClass/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        courseClassService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}