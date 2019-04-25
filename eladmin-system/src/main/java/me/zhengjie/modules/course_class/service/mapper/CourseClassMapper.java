package me.zhengjie.modules.course_class.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.course_class.domain.CourseClass;
import me.zhengjie.modules.course_class.service.dto.CourseClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseClassMapper extends EntityMapper<CourseClassDTO, CourseClass> {

}