package me.zhengjie.modules.reclist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.reclist.domain.Reclist;
import me.zhengjie.modules.reclist.service.dto.ReclistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReclistMapper extends EntityMapper<ReclistDTO, Reclist> {

}