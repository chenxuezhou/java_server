package me.zhengjie.modules.dish.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.dish.domain.Dish;
import me.zhengjie.modules.dish.service.dto.DishDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-30
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper extends EntityMapper<DishDTO, Dish> {

}