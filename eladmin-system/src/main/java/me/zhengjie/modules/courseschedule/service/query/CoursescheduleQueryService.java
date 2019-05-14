package me.zhengjie.modules.courseschedule.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.courseschedule.domain.Courseschedule;
import me.zhengjie.modules.courseschedule.service.dto.CoursescheduleDTO;
import me.zhengjie.modules.courseschedule.repository.CoursescheduleRepository;
import me.zhengjie.modules.courseschedule.service.mapper.CoursescheduleMapper;
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
@CacheConfig(cacheNames = "courseschedule")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CoursescheduleQueryService {

    @Autowired
    private CoursescheduleRepository coursescheduleRepository;

    @Autowired
    private CoursescheduleMapper coursescheduleMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CoursescheduleDTO courseschedule, Pageable pageable){
        Page<Courseschedule> page = coursescheduleRepository.findAll(new Spec(courseschedule),pageable);
        return PageUtil.toPage(page.map(coursescheduleMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CoursescheduleDTO courseschedule){
        return coursescheduleMapper.toDto(coursescheduleRepository.findAll(new Spec(courseschedule)));
    }

    class Spec implements Specification<Courseschedule> {

        private CoursescheduleDTO courseschedule;

        public Spec(CoursescheduleDTO courseschedule){
            this.courseschedule = courseschedule;
        }

        @Override
        public Predicate toPredicate(Root<Courseschedule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}