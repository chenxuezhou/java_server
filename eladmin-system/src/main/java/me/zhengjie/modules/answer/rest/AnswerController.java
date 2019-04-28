package me.zhengjie.modules.answer.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.answer.domain.Answer;
import me.zhengjie.modules.answer.service.AnswerService;
import me.zhengjie.modules.answer.service.dto.AnswerDTO;
import me.zhengjie.modules.answer.service.query.AnswerQueryService;
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
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerQueryService answerQueryService;

    private static final String ENTITY_NAME = "answer";

    @Log("查询Answer")
    @GetMapping(value = "/answer")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getAnswers(AnswerDTO resources, Pageable pageable){
        return new ResponseEntity(answerQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Answer")
    @PostMapping(value = "/answer")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Answer resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(answerService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Answer")
    @PutMapping(value = "/answer")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Answer resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        answerService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Answer")
    @DeleteMapping(value = "/answer/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        answerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}