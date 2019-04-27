package me.zhengjie.modules.collect.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.collect.domain.Collect;
import me.zhengjie.modules.collect.service.CollectService;
import me.zhengjie.modules.collect.service.dto.CollectDTO;
import me.zhengjie.modules.collect.service.query.CollectQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-27
*/
@RestController
@RequestMapping("api")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @Autowired
    private CollectQueryService collectQueryService;

    private static final String ENTITY_NAME = "collect";

    @Log("查询Collect")
    @GetMapping(value = "/collect")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCollects(CollectDTO resources, Pageable pageable){
        return new ResponseEntity(collectQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Collect")
    @PostMapping(value = "/collect")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Collect resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(collectService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Collect")
    @PutMapping(value = "/collect")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Collect resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        collectService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Collect")
    @DeleteMapping(value = "/collect/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        collectService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}