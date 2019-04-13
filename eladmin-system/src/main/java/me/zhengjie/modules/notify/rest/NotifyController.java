package me.zhengjie.modules.notify.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.notify.domain.Notify;
import me.zhengjie.modules.notify.service.NotifyService;
import me.zhengjie.modules.notify.service.dto.NotifyDTO;
import me.zhengjie.modules.notify.service.query.NotifyQueryService;
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
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private NotifyQueryService notifyQueryService;

    private static final String ENTITY_NAME = "notify";

    @Log("查询Notify")
    @GetMapping(value = "/notify")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getNotifys(NotifyDTO resources, Pageable pageable){
        return new ResponseEntity(notifyQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Notify")
    @PostMapping(value = "/notify")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Notify resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(notifyService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Notify")
    @PutMapping(value = "/notify")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Notify resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        notifyService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Notify")
    @DeleteMapping(value = "/notify/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        notifyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}