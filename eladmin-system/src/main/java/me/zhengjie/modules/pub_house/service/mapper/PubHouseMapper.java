package me.zhengjie.modules.pub_house.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.pub_house.domain.PubHouse;
import me.zhengjie.modules.pub_house.service.dto.PubHouseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-04
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PubHouseMapper extends EntityMapper<PubHouseDTO, PubHouse> {

}