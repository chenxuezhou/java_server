package me.zhengjie.modules.student.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.modules.student.service.StudentService;
import me.zhengjie.modules.student.service.dto.StudentDTO;
import me.zhengjie.modules.student.service.query.StudentQueryService;
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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentQueryService studentQueryService;

    private static final String ENTITY_NAME = "student";

    @Log("查询Student")
    @GetMapping(value = "/student")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getStudents(StudentDTO resources, Pageable pageable){
        return new ResponseEntity(studentQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Student")
    @PostMapping(value = "/student")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Student resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(studentService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Student")
    @PutMapping(value = "/student")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Student resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        studentService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Student")
    @DeleteMapping(value = "/student/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String id){
        studentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}