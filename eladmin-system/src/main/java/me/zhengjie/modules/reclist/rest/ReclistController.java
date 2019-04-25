package me.zhengjie.modules.reclist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.reclist.domain.Reclist;
import me.zhengjie.modules.reclist.service.ReclistService;
import me.zhengjie.modules.reclist.service.dto.ReclistDTO;
import me.zhengjie.modules.reclist.service.query.ReclistQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-24
*/
@RestController
@RequestMapping("api")
public class ReclistController {

    @Autowired
    private ReclistService reclistService;

    @Autowired
    private ReclistQueryService reclistQueryService;

    private static final String ENTITY_NAME = "reclist";

    @Log("查询Reclist")
    @GetMapping(value = "/reclist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getReclists(ReclistDTO resources, Pageable pageable){
        return new ResponseEntity(reclistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Reclist")
    @PostMapping(value = "/reclist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Reclist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(reclistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Reclist")
    @PutMapping(value = "/reclist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Reclist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        reclistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Reclist")
    @DeleteMapping(value = "/reclist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        reclistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}