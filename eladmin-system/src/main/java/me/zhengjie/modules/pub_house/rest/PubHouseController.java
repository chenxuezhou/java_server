package me.zhengjie.modules.pub_house.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.pub_house.domain.PubHouse;
import me.zhengjie.modules.pub_house.service.PubHouseService;
import me.zhengjie.modules.pub_house.service.dto.PubHouseDTO;
import me.zhengjie.modules.pub_house.service.query.PubHouseQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-04
*/
@RestController
@RequestMapping("api")
public class PubHouseController {

    @Autowired
    private PubHouseService pubHouseService;

    @Autowired
    private PubHouseQueryService pubHouseQueryService;

    private static final String ENTITY_NAME = "pubHouse";

    @Log("查询PubHouse")
    @GetMapping(value = "/pubHouse")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPubHouses(PubHouseDTO resources, Pageable pageable){
        return new ResponseEntity(pubHouseQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增PubHouse")
    @PostMapping(value = "/pubHouse")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody PubHouse resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(pubHouseService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改PubHouse")
    @PutMapping(value = "/pubHouse")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody PubHouse resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        pubHouseService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除PubHouse")
    @DeleteMapping(value = "/pubHouse/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        pubHouseService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}