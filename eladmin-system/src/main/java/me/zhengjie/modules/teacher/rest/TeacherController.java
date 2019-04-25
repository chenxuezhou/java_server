package me.zhengjie.modules.teacher.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.modules.teacher.service.TeacherService;
import me.zhengjie.modules.teacher.service.dto.TeacherDTO;
import me.zhengjie.modules.teacher.service.query.TeacherQueryService;
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
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherQueryService teacherQueryService;

    private static final String ENTITY_NAME = "teacher";

    @Log("查询Teacher")
    @GetMapping(value = "/teacher")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTeachers(TeacherDTO resources, Pageable pageable){
        return new ResponseEntity(teacherQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Teacher")
    @PostMapping(value = "/teacher")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Teacher resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(teacherService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Teacher")
    @PutMapping(value = "/teacher")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Teacher resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        teacherService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Teacher")
    @DeleteMapping(value = "/teacher/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        teacherService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}