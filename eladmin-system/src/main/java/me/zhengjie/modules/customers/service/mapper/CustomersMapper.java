package me.zhengjie.modules.customers.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.customers.domain.Customers;
import me.zhengjie.modules.customers.service.dto.CustomersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomersMapper extends EntityMapper<CustomersDTO, Customers> {

}