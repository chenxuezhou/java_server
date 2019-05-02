package me.zhengjie.modules.address.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.address.domain.Address;
import me.zhengjie.modules.address.service.AddressService;
import me.zhengjie.modules.address.service.dto.AddressDTO;
import me.zhengjie.modules.address.service.query.AddressQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-29
*/
@RestController
@RequestMapping("api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressQueryService addressQueryService;

    private static final String ENTITY_NAME = "address";

    @Log("查询Address")
    @GetMapping(value = "/address")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity getAddresss(AddressDTO resources, Pageable pageable){
        return new ResponseEntity(addressQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Address")
    @PostMapping(value = "/address")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity create(@Validated @RequestBody Address resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(addressService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Address")
    @PutMapping(value = "/address")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity update(@Validated @RequestBody Address resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        addressService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Address")
    @DeleteMapping(value = "/address/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL')")
    public ResponseEntity delete(@PathVariable Long id){
        addressService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}