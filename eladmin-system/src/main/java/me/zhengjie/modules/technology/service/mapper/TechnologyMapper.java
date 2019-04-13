package me.zhengjie.modules.technology.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.technology.domain.Technology;
import me.zhengjie.modules.technology.service.dto.TechnologyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TechnologyMapper extends EntityMapper<TechnologyDTO, Technology> {

}