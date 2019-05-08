package me.zhengjie.modules.movies.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.movies.domain.Movies;
import me.zhengjie.modules.movies.service.MoviesService;
import me.zhengjie.modules.movies.service.dto.MoviesDTO;
import me.zhengjie.modules.movies.service.query.MoviesQueryService;
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
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private MoviesQueryService moviesQueryService;

    private static final String ENTITY_NAME = "movies";

    @Log("查询Movies")
    @GetMapping(value = "/movies")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getMoviess(MoviesDTO resources, Pageable pageable){
        return new ResponseEntity(moviesQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Movies")
    @PostMapping(value = "/movies")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Movies resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(moviesService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Movies")
    @PutMapping(value = "/movies")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Movies resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        moviesService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Movies")
    @DeleteMapping(value = "/movies/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        moviesService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}