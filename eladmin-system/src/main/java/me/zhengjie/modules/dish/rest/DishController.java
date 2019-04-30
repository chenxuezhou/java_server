package me.zhengjie.modules.dish.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.dish.domain.Dish;
import me.zhengjie.modules.dish.service.DishService;
import me.zhengjie.modules.dish.service.dto.DishDTO;
import me.zhengjie.modules.dish.service.query.DishQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-30
*/
@RestController
@RequestMapping("api")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishQueryService dishQueryService;

    private static final String ENTITY_NAME = "dish";

    @Log("查询Dish")
    @GetMapping(value = "/dish")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getDishs(DishDTO resources, Pageable pageable){
        return new ResponseEntity(dishQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Dish")
    @PostMapping(value = "/dish")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Dish resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(dishService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Dish")
    @PutMapping(value = "/dish")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Dish resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        dishService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Dish")
    @DeleteMapping(value = "/dish/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        dishService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}