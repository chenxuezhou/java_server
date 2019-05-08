package me.zhengjie.modules.userlog.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.userlog.domain.Userlog;
import me.zhengjie.modules.userlog.service.UserlogService;
import me.zhengjie.modules.userlog.service.dto.UserlogDTO;
import me.zhengjie.modules.userlog.service.query.UserlogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-07
*/
@RestController
@RequestMapping("api")
public class UserlogController {

    @Autowired
    private UserlogService userlogService;

    @Autowired
    private UserlogQueryService userlogQueryService;

    private static final String ENTITY_NAME = "userlog";

    @Log("查询Userlog")
    @GetMapping(value = "/userlog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getUserlogs(UserlogDTO resources, Pageable pageable){
        return new ResponseEntity(userlogQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Userlog")
    @PostMapping(value = "/userlog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Userlog resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(userlogService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Userlog")
    @PutMapping(value = "/userlog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Userlog resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        userlogService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Userlog")
    @DeleteMapping(value = "/userlog/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        userlogService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}