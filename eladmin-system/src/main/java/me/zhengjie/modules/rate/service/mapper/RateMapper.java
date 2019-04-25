package me.zhengjie.modules.rate.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.rate.domain.Rate;
import me.zhengjie.modules.rate.service.dto.RateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RateMapper extends EntityMapper<RateDTO, Rate> {

}