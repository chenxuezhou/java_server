package me.zhengjie.modules.paper.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.paper.domain.Paper;
import me.zhengjie.modules.paper.service.dto.PaperDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-03-12
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaperMapper extends EntityMapper<PaperDTO, Paper> {

}