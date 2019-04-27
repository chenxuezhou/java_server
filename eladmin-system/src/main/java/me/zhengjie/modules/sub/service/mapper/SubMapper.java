package me.zhengjie.modules.sub.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.sub.domain.Sub;
import me.zhengjie.modules.sub.service.dto.SubDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-27
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubMapper extends EntityMapper<SubDTO, Sub> {

}