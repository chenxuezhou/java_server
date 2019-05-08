package me.zhengjie.modules.colmovie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.colmovie.domain.Colmovie;
import me.zhengjie.modules.colmovie.service.ColmovieService;
import me.zhengjie.modules.colmovie.service.dto.ColmovieDTO;
import me.zhengjie.modules.colmovie.service.query.ColmovieQueryService;
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
public class ColmovieController {

    @Autowired
    private ColmovieService colmovieService;

    @Autowired
    private ColmovieQueryService colmovieQueryService;

    private static final String ENTITY_NAME = "colmovie";

    @Log("查询Colmovie")
    @GetMapping(value = "/colmovie")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getColmovies(ColmovieDTO resources, Pageable pageable){
        return new ResponseEntity(colmovieQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Colmovie")
    @PostMapping(value = "/colmovie")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Colmovie resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(colmovieService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Colmovie")
    @PutMapping(value = "/colmovie")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Colmovie resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        colmovieService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Colmovie")
    @DeleteMapping(value = "/colmovie/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        colmovieService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}