package me.zhengjie.modules.comments.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.comments.domain.Comments;
import me.zhengjie.modules.comments.service.dto.CommentsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-07
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentsMapper extends EntityMapper<CommentsDTO, Comments> {

}