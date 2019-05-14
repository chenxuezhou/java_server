package me.zhengjie.modules.mark.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.modules.mark.service.dto.MarkDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-14
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarkMapper extends EntityMapper<MarkDTO, Mark> {

}