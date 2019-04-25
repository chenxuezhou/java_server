package me.zhengjie.modules.classdata.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.classdata.domain.Classdata;
import me.zhengjie.modules.classdata.service.dto.ClassdataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassdataMapper extends EntityMapper<ClassdataDTO, Classdata> {

}