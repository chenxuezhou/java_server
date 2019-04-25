package me.zhengjie.modules.chapterlist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.chapterlist.domain.Chapterlist;
import me.zhengjie.modules.chapterlist.service.dto.ChapterlistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-24
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChapterlistMapper extends EntityMapper<ChapterlistDTO, Chapterlist> {

}