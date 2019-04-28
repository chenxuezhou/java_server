package me.zhengjie.modules.testlist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.testlist.domain.Testlist;
import me.zhengjie.modules.testlist.service.TestlistService;
import me.zhengjie.modules.testlist.service.dto.TestlistDTO;
import me.zhengjie.modules.testlist.service.query.TestlistQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-28
*/
@RestController
@RequestMapping("api")
public class TestlistController {

    @Autowired
    private TestlistService testlistService;

    @Autowired
    private TestlistQueryService testlistQueryService;

    private static final String ENTITY_NAME = "testlist";

    @Log("查询Testlist")
    @GetMapping(value = "/testlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTestlists(TestlistDTO resources, Pageable pageable){
        return new ResponseEntity(testlistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Testlist")
    @PostMapping(value = "/testlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Testlist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(testlistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Testlist")
    @PutMapping(value = "/testlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Testlist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        testlistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Testlist")
    @DeleteMapping(value = "/testlist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        testlistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}