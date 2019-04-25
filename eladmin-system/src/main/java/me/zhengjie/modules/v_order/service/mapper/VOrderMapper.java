package me.zhengjie.modules.v_order.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.v_order.domain.VOrder;
import me.zhengjie.modules.v_order.service.dto.VOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-25
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VOrderMapper extends EntityMapper<VOrderDTO, VOrder> {

}