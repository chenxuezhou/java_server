package me.zhengjie.modules.rate.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.rate.domain.Rate;
import me.zhengjie.modules.rate.service.dto.RateDTO;
import me.zhengjie.modules.rate.repository.RateRepository;
import me.zhengjie.modules.rate.service.mapper.RateMapper;
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
@CacheConfig(cacheNames = "rate")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RateQueryService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RateMapper rateMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RateDTO rate, Pageable pageable){
        Page<Rate> page = rateRepository.findAll(new Spec(rate),pageable);
        return PageUtil.toPage(page.map(rateMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RateDTO rate){
        return rateMapper.toDto(rateRepository.findAll(new Spec(rate)));
    }

    class Spec implements Specification<Rate> {

        private RateDTO rate;

        public Spec(RateDTO rate){
            this.rate = rate;
        }

        @Override
        public Predicate toPredicate(Root<Rate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(rate.getRate())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("rate").as(String.class),rate.getRate()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}