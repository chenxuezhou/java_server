package me.zhengjie.modules.publish.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.publish.domain.PubContent;
import me.zhengjie.modules.publish.service.dto.PubContentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-26
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PubContentMapper extends EntityMapper<PubContentDTO, PubContent> {

}