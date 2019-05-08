package me.zhengjie.modules.tvs.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.tvs.domain.Tvs;
import me.zhengjie.modules.tvs.service.dto.TvsDTO;
import me.zhengjie.modules.tvs.repository.TvsRepository;
import me.zhengjie.modules.tvs.service.mapper.TvsMapper;
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
@CacheConfig(cacheNames = "tvs")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TvsQueryService {

    @Autowired
    private TvsRepository tvsRepository;

    @Autowired
    private TvsMapper tvsMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TvsDTO tvs, Pageable pageable){
        Page<Tvs> page = tvsRepository.findAll(new Spec(tvs),pageable);
        return PageUtil.toPage(page.map(tvsMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TvsDTO tvs){
        return tvsMapper.toDto(tvsRepository.findAll(new Spec(tvs)));
    }

    class Spec implements Specification<Tvs> {

        private TvsDTO tvs;

        public Spec(TvsDTO tvs){
            this.tvs = tvs;
        }

        @Override
        public Predicate toPredicate(Root<Tvs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(tvs.getTitle())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+tvs.getTitle()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}