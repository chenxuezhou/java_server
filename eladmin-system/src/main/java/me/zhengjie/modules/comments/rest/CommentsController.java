package me.zhengjie.modules.comments.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.comments.domain.Comments;
import me.zhengjie.modules.comments.service.CommentsService;
import me.zhengjie.modules.comments.service.dto.CommentsDTO;
import me.zhengjie.modules.comments.service.query.CommentsQueryService;
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
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsQueryService commentsQueryService;

    private static final String ENTITY_NAME = "comments";

    @Log("查询Comments")
    @GetMapping(value = "/comments")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getCommentss(CommentsDTO resources, Pageable pageable){
        return new ResponseEntity(commentsQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Comments")
    @PostMapping(value = "/comments")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Comments resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(commentsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Comments")
    @PutMapping(value = "/comments")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Comments resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        commentsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Comments")
    @DeleteMapping(value = "/comments/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        commentsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}