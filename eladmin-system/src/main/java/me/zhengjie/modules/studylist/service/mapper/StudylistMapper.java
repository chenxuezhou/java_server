package me.zhengjie.modules.studylist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.studylist.domain.Studylist;
import me.zhengjie.modules.studylist.service.dto.StudylistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-28
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudylistMapper extends EntityMapper<StudylistDTO, Studylist> {

}