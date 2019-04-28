package me.zhengjie.modules.errorlist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.errorlist.domain.Errorlist;
import me.zhengjie.modules.errorlist.service.ErrorlistService;
import me.zhengjie.modules.errorlist.service.dto.ErrorlistDTO;
import me.zhengjie.modules.errorlist.service.query.ErrorlistQueryService;
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
public class ErrorlistController {

    @Autowired
    private ErrorlistService errorlistService;

    @Autowired
    private ErrorlistQueryService errorlistQueryService;

    private static final String ENTITY_NAME = "errorlist";

    @Log("查询Errorlist")
    @GetMapping(value = "/errorlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getErrorlists(ErrorlistDTO resources, Pageable pageable){
        return new ResponseEntity(errorlistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Errorlist")
    @PostMapping(value = "/errorlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Errorlist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(errorlistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Errorlist")
    @PutMapping(value = "/errorlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Errorlist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        errorlistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Errorlist")
    @DeleteMapping(value = "/errorlist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        errorlistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}