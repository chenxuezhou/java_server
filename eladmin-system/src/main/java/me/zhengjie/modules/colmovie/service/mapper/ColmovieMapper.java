package me.zhengjie.modules.colmovie.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.colmovie.domain.Colmovie;
import me.zhengjie.modules.colmovie.service.dto.ColmovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ColmovieMapper extends EntityMapper<ColmovieDTO, Colmovie> {

}