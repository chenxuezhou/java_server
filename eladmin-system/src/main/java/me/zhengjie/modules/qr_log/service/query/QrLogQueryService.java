package me.zhengjie.modules.qr_log.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.qr_log.domain.QrLog;
import me.zhengjie.modules.qr_log.service.dto.QrLogDTO;
import me.zhengjie.modules.qr_log.repository.QrLogRepository;
import me.zhengjie.modules.qr_log.service.mapper.QrLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "qrLog")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QrLogQueryService {

    @Autowired
    private QrLogRepository qrLogRepository;

    @Autowired
    private QrLogMapper qrLogMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(QrLogDTO qrLog, Pageable pageable){
        Page<QrLog> page = qrLogRepository.findAll(new Spec(qrLog),pageable);
        return PageUtil.toPage(page.map(qrLogMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(QrLogDTO qrLog){
        return qrLogMapper.toDto(qrLogRepository.findAll(new Spec(qrLog)));
    }

    class Spec implements Specification<QrLog> {

        private QrLogDTO qrLog;

        public Spec(QrLogDTO qrLog){
            this.qrLog = qrLog;
        }

        @Override
        public Predicate toPredicate(Root<QrLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(qrLog.getUsername())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("username").as(String.class),"%"+qrLog.getUsername()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}