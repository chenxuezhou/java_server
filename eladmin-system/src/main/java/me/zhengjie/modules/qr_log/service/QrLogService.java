package me.zhengjie.modules.qr_log.service;

import me.zhengjie.modules.qr_log.domain.QrLog;
import me.zhengjie.modules.qr_log.service.dto.QrLogDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-11
*/
@CacheConfig(cacheNames = "qrLog")
public interface QrLogService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    QrLogDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    QrLogDTO create(QrLog resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(QrLog resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);
}