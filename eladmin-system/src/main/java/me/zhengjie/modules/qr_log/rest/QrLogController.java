package me.zhengjie.modules.qr_log.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.qr_log.domain.QrLog;
import me.zhengjie.modules.qr_log.service.QrLogService;
import me.zhengjie.modules.qr_log.service.dto.QrLogDTO;
import me.zhengjie.modules.qr_log.service.query.QrLogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-05-11
*/
@RestController
@RequestMapping("api")
public class QrLogController {

    @Autowired
    private QrLogService qrLogService;

    @Autowired
    private QrLogQueryService qrLogQueryService;

    private static final String ENTITY_NAME = "qrLog";

    @Log("查询QrLog")
    @GetMapping(value = "/qrLog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getQrLogs(QrLogDTO resources, Pageable pageable){
        return new ResponseEntity(qrLogQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增QrLog")
    @PostMapping(value = "/qrLog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody QrLog resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(qrLogService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改QrLog")
    @PutMapping(value = "/qrLog")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody QrLog resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        qrLogService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除QrLog")
    @DeleteMapping(value = "/qrLog/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        qrLogService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}