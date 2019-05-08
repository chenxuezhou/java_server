package me.zhengjie.modules.tvs.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.tvs.domain.Tvs;
import me.zhengjie.modules.tvs.service.dto.TvsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TvsMapper extends EntityMapper<TvsDTO, Tvs> {

}