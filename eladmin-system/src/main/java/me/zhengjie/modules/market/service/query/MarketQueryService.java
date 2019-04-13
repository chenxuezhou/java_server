package me.zhengjie.modules.market.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.market.domain.Market;
import me.zhengjie.modules.market.service.dto.MarketDTO;
import me.zhengjie.modules.market.repository.MarketRepository;
import me.zhengjie.modules.market.service.mapper.MarketMapper;
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
@CacheConfig(cacheNames = "market")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MarketQueryService {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private MarketMapper marketMapper;

    /**
     * ��ҳ
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MarketDTO market, Pageable pageable){
        Page<Market> page = marketRepository.findAll(new Spec(market),pageable);
        return PageUtil.toPage(page.map(marketMapper::toDto));
    }

    /**
    * ����ҳ
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MarketDTO market){
        return marketMapper.toDto(marketRepository.findAll(new Spec(market)));
    }

    class Spec implements Specification<Market> {

        private MarketDTO market;

        public Spec(MarketDTO market){
            this.market = market;
        }

        @Override
        public Predicate toPredicate(Root<Market> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(market.getCreateTime())){
                    /**
                    * ��ȷ
                    */
                    list.add(cb.equal(root.get("create_time").as(Timestamp.class),market.getCreateTime()));
                }
                if(!ObjectUtils.isEmpty(market.getTitle())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+market.getTitle()+"%"));
                }
                if(!ObjectUtils.isEmpty(market.getNumber())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("number").as(String.class),"%"+market.getNumber()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}