package me.zhengjie.modules.typelist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.typelist.domain.Typelist;
import me.zhengjie.modules.typelist.service.dto.TypelistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypelistMapper extends EntityMapper<TypelistDTO, Typelist> {

}