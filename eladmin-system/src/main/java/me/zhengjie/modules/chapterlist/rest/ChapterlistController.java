package me.zhengjie.modules.chapterlist.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.chapterlist.domain.Chapterlist;
import me.zhengjie.modules.chapterlist.service.ChapterlistService;
import me.zhengjie.modules.chapterlist.service.dto.ChapterlistDTO;
import me.zhengjie.modules.chapterlist.service.query.ChapterlistQueryService;
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
public class ChapterlistController {

    @Autowired
    private ChapterlistService chapterlistService;

    @Autowired
    private ChapterlistQueryService chapterlistQueryService;

    private static final String ENTITY_NAME = "chapterlist";

    @Log("查询Chapterlist")
    @GetMapping(value = "/chapterlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getChapterlists(ChapterlistDTO resources, Pageable pageable){
        return new ResponseEntity(chapterlistQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Chapterlist")
    @PostMapping(value = "/chapterlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Chapterlist resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(chapterlistService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Chapterlist")
    @PutMapping(value = "/chapterlist")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Chapterlist resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        chapterlistService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Chapterlist")
    @DeleteMapping(value = "/chapterlist/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        chapterlistService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}