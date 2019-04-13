package me.zhengjie.modules.peccancy.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.peccancy.domain.Peccancy;
import me.zhengjie.modules.peccancy.service.PeccancyService;
import me.zhengjie.modules.peccancy.service.dto.PeccancyDTO;
import me.zhengjie.modules.peccancy.service.query.PeccancyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-03-12
*/
@RestController
@RequestMapping("api")
public class PeccancyController {

    @Autowired
    private PeccancyService peccancyService;

    @Autowired
    private PeccancyQueryService peccancyQueryService;

    private static final String ENTITY_NAME = "peccancy";

    @Log("查询Peccancy")
    @GetMapping(value = "/peccancy")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPeccancys(PeccancyDTO resources, Pageable pageable){
        return new ResponseEntity(peccancyQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Peccancy")
    @PostMapping(value = "/peccancy")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Peccancy resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(peccancyService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Peccancy")
    @PutMapping(value = "/peccancy")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Peccancy resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        peccancyService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Peccancy")
    @DeleteMapping(value = "/peccancy/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        peccancyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}