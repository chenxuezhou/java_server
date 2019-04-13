package me.zhengjie.modules.meet.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.meet.domain.Meeting;
import me.zhengjie.modules.meet.service.MeetingService;
import me.zhengjie.modules.meet.service.dto.MeetingDTO;
import me.zhengjie.modules.meet.service.query.MeetingQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author jie
* @date 2019-04-13
*/
@RestController
@RequestMapping("api")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingQueryService meetingQueryService;

    private static final String ENTITY_NAME = "meeting";

    @Log("查询Meeting")
    @GetMapping(value = "/meeting")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getMeetings(MeetingDTO resources, Pageable pageable){
        return new ResponseEntity(meetingQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Meeting")
    @PostMapping(value = "/meeting")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Meeting resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(meetingService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Meeting")
    @PutMapping(value = "/meeting")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Meeting resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        meetingService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Meeting")
    @DeleteMapping(value = "/meeting/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        meetingService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}