package me.zhengjie.modules.peccancy.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.peccancy.domain.Peccancy;
import me.zhengjie.modules.peccancy.service.dto.PeccancyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PeccancyMapper extends EntityMapper<PeccancyDTO, Peccancy> {

}