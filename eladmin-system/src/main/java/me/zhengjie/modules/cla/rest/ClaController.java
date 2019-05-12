package me.zhengjie.modules.cla.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.cla.domain.Cla;
import me.zhengjie.modules.cla.service.ClaService;
import me.zhengjie.modules.cla.service.dto.ClaDTO;
import me.zhengjie.modules.cla.service.query.ClaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-12
*/
@RestController
@RequestMapping("api")
public class ClaController {

    @Autowired
    private ClaService claService;

    @Autowired
    private ClaQueryService claQueryService;

    private static final String ENTITY_NAME = "cla";

    @Log("查询Cla")
    @GetMapping(value = "/cla")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getClas(ClaDTO resources, Pageable pageable){
        return new ResponseEntity(claQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Cla")
    @PostMapping(value = "/cla")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Cla resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(claService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Cla")
    @PutMapping(value = "/cla")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Cla resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        claService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Cla")
    @DeleteMapping(value = "/cla/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        claService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}