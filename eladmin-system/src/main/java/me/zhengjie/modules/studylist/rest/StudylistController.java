package me.zhengjie.modules.studylist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.studylist.domain.Studylist;
import me.zhengjie.modules.studylist.service.StudylistService;
import me.zhengjie.modules.studylist.service.dto.StudylistDTO;
import me.zhengjie.modules.studylist.service.query.StudylistQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-28
*/
@RestController
@RequestMapping("api")
public class StudylistController {

    @Autowired
    private StudylistService studylistService;

    @Autowired
    private StudylistQueryService studylistQueryService;

    private static final String ENTITY_NAME = "studylist";

    @Log("查询Studylist")
    @GetMapping(value = "/studylist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getStudylists(StudylistDTO resources, Pageable pageable){
        return new ResponseEntity(studylistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Studylist")
    @PostMapping(value = "/studylist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Studylist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(studylistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Studylist")
    @PutMapping(value = "/studylist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Studylist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        studylistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Studylist")
    @DeleteMapping(value = "/studylist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        studylistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}