package me.zhengjie.modules.userlog.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.userlog.domain.Userlog;
import me.zhengjie.modules.userlog.service.dto.UserlogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserlogMapper extends EntityMapper<UserlogDTO, Userlog> {

}