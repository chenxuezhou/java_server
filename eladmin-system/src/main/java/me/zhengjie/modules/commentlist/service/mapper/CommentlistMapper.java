package me.zhengjie.modules.commentlist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.commentlist.domain.Commentlist;
import me.zhengjie.modules.commentlist.service.dto.CommentlistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentlistMapper extends EntityMapper<CommentlistDTO, Commentlist> {

}