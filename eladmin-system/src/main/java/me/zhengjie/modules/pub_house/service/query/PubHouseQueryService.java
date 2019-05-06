package me.zhengjie.modules.pub_house.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.pub_house.domain.PubHouse;
import me.zhengjie.modules.pub_house.service.dto.PubHouseDTO;
import me.zhengjie.modules.pub_house.repository.PubHouseRepository;
import me.zhengjie.modules.pub_house.service.mapper.PubHouseMapper;
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
@CacheConfig(cacheNames = "pubHouse")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PubHouseQueryService {

    @Autowired
    private PubHouseRepository pubHouseRepository;

    @Autowired
    private PubHouseMapper pubHouseMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PubHouseDTO pubHouse, Pageable pageable){
        Page<PubHouse> page = pubHouseRepository.findAll(new Spec(pubHouse),pageable);
        return PageUtil.toPage(page.map(pubHouseMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PubHouseDTO pubHouse){
        return pubHouseMapper.toDto(pubHouseRepository.findAll(new Spec(pubHouse)));
    }

    class Spec implements Specification<PubHouse> {

        private PubHouseDTO pubHouse;

        public Spec(PubHouseDTO pubHouse){
            this.pubHouse = pubHouse;
        }

        @Override
        public Predicate toPredicate(Root<PubHouse> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(pubHouse.getToiletnum())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("toiletNum").as(String.class),pubHouse.getToiletnum()));
                }
                if(!ObjectUtils.isEmpty(pubHouse.getParlournum())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("parlourNum").as(String.class),pubHouse.getParlournum()));
                }
                if(!ObjectUtils.isEmpty(pubHouse.getName())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("name").as(String.class),"%"+pubHouse.getName()+"%"));
                }
                if(!ObjectUtils.isEmpty(pubHouse.getBedroomnum())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("bedroomNum").as(String.class),pubHouse.getBedroomnum()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}