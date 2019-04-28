package me.zhengjie.modules.testlist.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.testlist.domain.Testlist;
import me.zhengjie.modules.testlist.service.dto.TestlistDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-04-28
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestlistMapper extends EntityMapper<TestlistDTO, Testlist> {

}