package me.zhengjie.modules.intro.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.intro.domain.Intro;
import me.zhengjie.modules.intro.service.IntroService;
import me.zhengjie.modules.intro.service.dto.IntroDTO;
import me.zhengjie.modules.intro.service.query.IntroQueryService;
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
public class IntroController {

    @Autowired
    private IntroService introService;

    @Autowired
    private IntroQueryService introQueryService;

    private static final String ENTITY_NAME = "intro";

    @Log("查询Intro")
    @GetMapping(value = "/intro")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getIntros(IntroDTO resources, Pageable pageable){
        return new ResponseEntity(introQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Intro")
    @PostMapping(value = "/intro")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Intro resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(introService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Intro")
    @PutMapping(value = "/intro")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Intro resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        introService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Intro")
    @DeleteMapping(value = "/intro/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        introService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}