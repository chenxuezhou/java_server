package me.zhengjie.modules.rate.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.rate.domain.Rate;
import me.zhengjie.modules.rate.service.RateService;
import me.zhengjie.modules.rate.service.dto.RateDTO;
import me.zhengjie.modules.rate.service.query.RateQueryService;
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
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private RateQueryService rateQueryService;

    private static final String ENTITY_NAME = "rate";

    @Log("查询Rate")
    @GetMapping(value = "/rate")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getRates(RateDTO resources, Pageable pageable){
        return new ResponseEntity(rateQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Rate")
    @PostMapping(value = "/rate")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Rate resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(rateService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Rate")
    @PutMapping(value = "/rate")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Rate resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        rateService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Rate")
    @DeleteMapping(value = "/rate/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        rateService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}