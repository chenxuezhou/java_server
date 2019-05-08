package me.zhengjie.modules.preview.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.preview.domain.Preview;
import me.zhengjie.modules.preview.service.dto.PreviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreviewMapper extends EntityMapper<PreviewDTO, Preview> {

}