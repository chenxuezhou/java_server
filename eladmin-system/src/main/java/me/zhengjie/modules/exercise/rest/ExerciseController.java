package me.zhengjie.modules.exercise.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.exercise.domain.Exercise;
import me.zhengjie.modules.exercise.service.ExerciseService;
import me.zhengjie.modules.exercise.service.dto.ExerciseDTO;
import me.zhengjie.modules.exercise.service.query.ExerciseQueryService;
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
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseQueryService exerciseQueryService;

    private static final String ENTITY_NAME = "exercise";

    @Log("查询Exercise")
    @GetMapping(value = "/exercise")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getExercises(ExerciseDTO resources, Pageable pageable){
        return new ResponseEntity(exerciseQueryService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @Log("新增Exercise")
    @PostMapping(value = "/exercise")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Validated @RequestBody Exercise resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity(exerciseService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Exercise")
    @PutMapping(value = "/exercise")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@Validated @RequestBody Exercise resources){
        if (resources.getId() == null) {
            throw new BadRequestException(ENTITY_NAME +" ID Can not be empty");
        }
        exerciseService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Exercise")
    @DeleteMapping(value = "/exercise/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id){
        exerciseService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}