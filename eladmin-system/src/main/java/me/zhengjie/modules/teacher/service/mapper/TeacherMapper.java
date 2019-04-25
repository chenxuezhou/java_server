package me.zhengjie.modules.teacher.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.modules.teacher.service.dto.TeacherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {

}