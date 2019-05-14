package me.zhengjie.modules.user_opt.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.user_opt.domain.Users;
import me.zhengjie.modules.user_opt.service.UsersService;
import me.zhengjie.modules.user_opt.service.dto.UsersDTO;
import me.zhengjie.modules.user_opt.service.query.UsersQueryService;
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
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersQueryService usersQueryService;

    private static final String ENTITY_NAME = "users";

    @Log("查询Users")
    @GetMapping(value = "/users1")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getUserss(UsersDTO resources, Pageable pageable){
        return new ResponseEntity(usersQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Users")
    @PostMapping(value = "/users1")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Users resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(usersService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Users")
    @PutMapping(value = "/users1")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Users resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        usersService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Users")
    @DeleteMapping(value = "/users1/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String id){
        usersService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}