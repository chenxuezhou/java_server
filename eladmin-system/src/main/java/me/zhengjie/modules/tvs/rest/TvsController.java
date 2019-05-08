package me.zhengjie.modules.tvs.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.tvs.domain.Tvs;
import me.zhengjie.modules.tvs.service.TvsService;
import me.zhengjie.modules.tvs.service.dto.TvsDTO;
import me.zhengjie.modules.tvs.service.query.TvsQueryService;
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
public class TvsController {

    @Autowired
    private TvsService tvsService;

    @Autowired
    private TvsQueryService tvsQueryService;

    private static final String ENTITY_NAME = "tvs";

    @Log("查询Tvs")
    @GetMapping(value = "/tvs")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTvss(TvsDTO resources, Pageable pageable){
        return new ResponseEntity(tvsQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Tvs")
    @PostMapping(value = "/tvs")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Tvs resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(tvsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Tvs")
    @PutMapping(value = "/tvs")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Tvs resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        tvsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Tvs")
    @DeleteMapping(value = "/tvs/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        tvsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}