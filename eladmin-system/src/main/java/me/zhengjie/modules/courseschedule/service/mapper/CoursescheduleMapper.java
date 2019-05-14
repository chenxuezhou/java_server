package me.zhengjie.modules.courseschedule.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.courseschedule.domain.Courseschedule;
import me.zhengjie.modules.courseschedule.service.dto.CoursescheduleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-14
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoursescheduleMapper extends EntityMapper<CoursescheduleDTO, Courseschedule> {

}