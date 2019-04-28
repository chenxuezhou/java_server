package me.zhengjie.modules.errorlist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.errorlist.domain.Errorlist;
import me.zhengjie.modules.errorlist.service.dto.ErrorlistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-28
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ErrorlistMapper extends EntityMapper<ErrorlistDTO, Errorlist> {

}