package me.zhengjie.modules.answer.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.answer.domain.Answer;
import me.zhengjie.modules.answer.service.dto.AnswerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-28
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {

}