package me.zhengjie.modules.student.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.modules.student.service.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-14
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

}