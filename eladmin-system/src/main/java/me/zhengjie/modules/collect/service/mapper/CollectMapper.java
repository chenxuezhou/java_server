package me.zhengjie.modules.collect.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.collect.domain.Collect;
import me.zhengjie.modules.collect.service.dto.CollectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-27
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectMapper extends EntityMapper<CollectDTO, Collect> {

}