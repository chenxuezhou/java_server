package me.zhengjie.modules.courselist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.courselist.domain.Courselist;
import me.zhengjie.modules.courselist.service.dto.CourselistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourselistMapper extends EntityMapper<CourselistDTO, Courselist> {

}