package me.zhengjie.modules.address.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.address.domain.Address;
import me.zhengjie.modules.address.service.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-29
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

}