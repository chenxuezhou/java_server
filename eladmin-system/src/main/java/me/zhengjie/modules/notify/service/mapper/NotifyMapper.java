package me.zhengjie.modules.notify.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.notify.domain.Notify;
import me.zhengjie.modules.notify.service.dto.NotifyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotifyMapper extends EntityMapper<NotifyDTO, Notify> {

}