package me.zhengjie.modules.intro.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.intro.domain.Intro;
import me.zhengjie.modules.intro.service.dto.IntroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IntroMapper extends EntityMapper<IntroDTO, Intro> {

}