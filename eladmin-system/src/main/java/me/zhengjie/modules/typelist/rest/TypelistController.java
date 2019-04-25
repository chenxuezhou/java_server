package me.zhengjie.modules.typelist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.typelist.domain.Typelist;
import me.zhengjie.modules.typelist.service.TypelistService;
import me.zhengjie.modules.typelist.service.dto.TypelistDTO;
import me.zhengjie.modules.typelist.service.query.TypelistQueryService;
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
public class TypelistController {

    @Autowired
    private TypelistService typelistService;

    @Autowired
    private TypelistQueryService typelistQueryService;

    private static final String ENTITY_NAME = "typelist";

    @Log("查询Typelist")
    @GetMapping(value = "/typelist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTypelists(TypelistDTO resources, Pageable pageable){
        return new ResponseEntity(typelistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Typelist")
    @PostMapping(value = "/typelist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Typelist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(typelistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Typelist")
    @PutMapping(value = "/typelist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Typelist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        typelistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Typelist")
    @DeleteMapping(value = "/typelist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        typelistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}