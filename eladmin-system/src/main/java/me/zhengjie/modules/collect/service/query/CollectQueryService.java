package me.zhengjie.modules.collect.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.collect.domain.Collect;
import me.zhengjie.modules.collect.service.dto.CollectDTO;
import me.zhengjie.modules.collect.repository.CollectRepository;
import me.zhengjie.modules.collect.service.mapper.CollectMapper;
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
@CacheConfig(cacheNames = "collect")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CollectQueryService {

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CollectDTO collect, Pageable pageable){
        Page<Collect> page = collectRepository.findAll(new Spec(collect),pageable);
        return PageUtil.toPage(page.map(collectMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CollectDTO collect){
        return collectMapper.toDto(collectRepository.findAll(new Spec(collect)));
    }

    class Spec implements Specification<Collect> {

        private CollectDTO collect;

        public Spec(CollectDTO collect){
            this.collect = collect;
        }

        @Override
        public Predicate toPredicate(Root<Collect> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}