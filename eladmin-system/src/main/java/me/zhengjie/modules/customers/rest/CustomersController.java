package me.zhengjie.modules.customers.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.customers.domain.Customers;
import me.zhengjie.modules.customers.service.CustomersService;
import me.zhengjie.modules.customers.service.dto.CustomersDTO;
import me.zhengjie.modules.customers.service.query.CustomersQueryService;
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
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private CustomersQueryService customersQueryService;

    private static final String ENTITY_NAME = "customers";

    @Log("查询Customers")
    @GetMapping(value = "/customers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCustomerss(CustomersDTO resources, Pageable pageable){
        return new ResponseEntity(customersQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Customers")
    @PostMapping(value = "/customers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Customers resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(customersService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Customers")
    @PutMapping(value = "/customers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Customers resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        customersService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Customers")
    @DeleteMapping(value = "/customers/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        customersService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}