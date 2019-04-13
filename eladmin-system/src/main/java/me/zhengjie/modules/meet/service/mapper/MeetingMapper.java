package me.zhengjie.modules.meet.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.meet.domain.Meeting;
import me.zhengjie.modules.meet.service.dto.MeetingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-13
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeetingMapper extends EntityMapper<MeetingDTO, Meeting> {

}