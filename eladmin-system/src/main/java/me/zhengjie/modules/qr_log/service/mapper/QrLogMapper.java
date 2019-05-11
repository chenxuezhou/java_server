package me.zhengjie.modules.qr_log.service.mapper;

import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.qr_log.domain.QrLog;
import me.zhengjie.modules.qr_log.service.dto.QrLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-05-11
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QrLogMapper extends EntityMapper<QrLogDTO, QrLog> {

}