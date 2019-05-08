package me.zhengjie.modules.preview.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.preview.domain.Preview;
import me.zhengjie.modules.preview.service.PreviewService;
import me.zhengjie.modules.preview.service.dto.PreviewDTO;
import me.zhengjie.modules.preview.service.query.PreviewQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-07
*/
@RestController
@RequestMapping("api")
public class PreviewController {

    @Autowired
    private PreviewService previewService;

    @Autowired
    private PreviewQueryService previewQueryService;

    private static final String ENTITY_NAME = "preview";

    @Log("查询Preview")
    @GetMapping(value = "/preview")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPreviews(PreviewDTO resources, Pageable pageable){
        return new ResponseEntity(previewQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Preview")
    @PostMapping(value = "/preview")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Preview resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(previewService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Preview")
    @PutMapping(value = "/preview")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Preview resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        previewService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Preview")
    @DeleteMapping(value = "/preview/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        previewService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}