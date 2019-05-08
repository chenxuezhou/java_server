package me.zhengjie.modules.movies.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.movies.domain.Movies;
import me.zhengjie.modules.movies.service.dto.MoviesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MoviesMapper extends EntityMapper<MoviesDTO, Movies> {

}