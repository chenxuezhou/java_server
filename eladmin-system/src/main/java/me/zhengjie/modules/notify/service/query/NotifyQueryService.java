package me.zhengjie.modules.notify.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.notify.domain.Notify;
import me.zhengjie.modules.notify.service.dto.NotifyDTO;
import me.zhengjie.modules.notify.repository.NotifyRepository;
import me.zhengjie.modules.notify.service.mapper.NotifyMapper;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "notify")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NotifyQueryService {

    @Autowired
    private NotifyRepository notifyRepository;

    @Autowired
    private NotifyMapper notifyMapper;

    /**
     * ��ҳ
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(NotifyDTO notify, Pageable pageable){
        Page<Notify> page = notifyRepository.findAll(new Spec(notify),pageable);
        return PageUtil.toPage(page.map(notifyMapper::toDto));
    }

    /**
    * ����ҳ
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(NotifyDTO notify){
        return notifyMapper.toDto(notifyRepository.findAll(new Spec(notify)));
    }

    class Spec implements Specification<Notify> {

        private NotifyDTO notify;

        public Spec(NotifyDTO notify){
            this.notify = notify;
        }

        @Override
        public Predicate toPredicate(Root<Notify> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(notify.getCreateTime())){
                    /**
                    * ��ȷ
                    */
                    list.add(cb.equal(root.get("create_time").as(Timestamp.class),notify.getCreateTime()));
                }
                if(!ObjectUtils.isEmpty(notify.getTitle())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+notify.getTitle()+"%"));
                }
                if(!ObjectUtils.isEmpty(notify.getNumber())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("number").as(String.class),"%"+notify.getNumber()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}