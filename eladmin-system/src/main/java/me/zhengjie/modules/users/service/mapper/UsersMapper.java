package me.zhengjie.modules.users.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.users.domain.Users;
import me.zhengjie.modules.users.service.dto.UsersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersMapper extends EntityMapper<UsersDTO, Users> {

}