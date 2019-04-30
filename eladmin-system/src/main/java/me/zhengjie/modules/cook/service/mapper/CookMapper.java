package me.zhengjie.modules.cook.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.cook.domain.Cook;
import me.zhengjie.modules.cook.service.dto.CookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-30
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CookMapper extends EntityMapper<CookDTO, Cook> {

}