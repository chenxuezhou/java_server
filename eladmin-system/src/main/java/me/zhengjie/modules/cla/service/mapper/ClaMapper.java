package me.zhengjie.modules.cla.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.cla.domain.Cla;
import me.zhengjie.modules.cla.service.dto.ClaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClaMapper extends EntityMapper<ClaDTO, Cla> {

}