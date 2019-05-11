package me.zhengjie.modules.qr_log.repository;

import me.zhengjie.modules.qr_log.domain.QrLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-11
*/
public interface QrLogRepository extends JpaRepository<QrLog, Long>, JpaSpecificationExecutor {
}