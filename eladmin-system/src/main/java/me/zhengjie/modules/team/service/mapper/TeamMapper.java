package me.zhengjie.modules.team.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.team.domain.Team;
import me.zhengjie.modules.team.service.dto.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-14
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {

}