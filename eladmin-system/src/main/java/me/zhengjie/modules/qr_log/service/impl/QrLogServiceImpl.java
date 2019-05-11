package me.zhengjie.modules.qr_log.service.impl;

import me.zhengjie.modules.qr_log.domain.QrLog;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.qr_log.repository.QrLogRepository;
import me.zhengjie.modules.qr_log.service.QrLogService;
import me.zhengjie.modules.qr_log.service.dto.QrLogDTO;
import me.zhengjie.modules.qr_log.service.mapper.QrLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-11
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QrLogServiceImpl implements QrLogService {

    @Autowired
    private QrLogRepository qrLogRepository;

    @Autowired
    private QrLogMapper qrLogMapper;

    @Override
    public QrLogDTO findById(Long id) {
        Optional<QrLog> qrLog = qrLogRepository.findById(id);
        ValidationUtil.isNull(qrLog,"QrLog","id",id);
        return qrLogMapper.toDto(qrLog.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QrLogDTO create(QrLog resources) {
        return qrLogMapper.toDto(qrLogRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(QrLog resources) {
        Optional<QrLog> optionalQrLog = qrLogRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalQrLog,"QrLog","id",resources.getId());

        QrLog qrLog = optionalQrLog.get();
        // 此处需自己修改
        resources.setId(qrLog.getId());
        qrLogRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        qrLogRepository.deleteById(id);
    }
}