package me.zhengjie.modules.exercise.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.exercise.domain.Exercise;
import me.zhengjie.modules.exercise.service.dto.ExerciseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-28
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExerciseMapper extends EntityMapper<ExerciseDTO, Exercise> {

}