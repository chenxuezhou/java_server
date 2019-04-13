package me.zhengjie.modules.technology.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.technology.domain.Technology;
import me.zhengjie.modules.technology.service.TechnologyService;
import me.zhengjie.modules.technology.service.dto.TechnologyDTO;
import me.zhengjie.modules.technology.service.query.TechnologyQueryService;
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
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private TechnologyQueryService technologyQueryService;

    private static final String ENTITY_NAME = "technology";

    @Log("查询Technology")
    @GetMapping(value = "/technology")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getTechnologys(TechnologyDTO resources, Pageable pageable){
        return new ResponseEntity(technologyQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Technology")
    @PostMapping(value = "/technology")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Technology resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(technologyService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Technology")
    @PutMapping(value = "/technology")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Technology resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        technologyService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Technology")
    @DeleteMapping(value = "/technology/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        technologyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}