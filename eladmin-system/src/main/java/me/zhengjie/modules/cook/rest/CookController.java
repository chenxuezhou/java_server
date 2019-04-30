package me.zhengjie.modules.cook.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.cook.domain.Cook;
import me.zhengjie.modules.cook.service.CookService;
import me.zhengjie.modules.cook.service.dto.CookDTO;
import me.zhengjie.modules.cook.service.query.CookQueryService;
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
public class CookController {

    @Autowired
    private CookService cookService;

    @Autowired
    private CookQueryService cookQueryService;

    private static final String ENTITY_NAME = "cook";

    @Log("查询Cook")
    @GetMapping(value = "/cook")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity getCooks(CookDTO resources, Pageable pageable){
        return new ResponseEntity(cookQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Cook")
    @PostMapping(value = "/cook")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity create(@Validated @RequestBody Cook resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(cookService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Cook")
    @PutMapping(value = "/cook")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity update(@Validated @RequestBody Cook resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        cookService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Cook")
    @DeleteMapping(value = "/cook/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity delete(@PathVariable Long id){
        cookService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}