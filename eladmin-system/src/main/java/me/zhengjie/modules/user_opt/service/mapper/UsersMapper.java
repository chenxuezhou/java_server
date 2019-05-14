package me.zhengjie.modules.user_opt.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.user_opt.domain.Users;
import me.zhengjie.modules.user_opt.service.dto.UsersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-14
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersMapper extends EntityMapper<UsersDTO, Users> {

}