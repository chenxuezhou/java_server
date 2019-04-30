package me.zhengjie.modules.cook.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.cook.domain.Cook;
import me.zhengjie.modules.cook.service.dto.CookDTO;
import me.zhengjie.modules.cook.repository.CookRepository;
import me.zhengjie.modules.cook.service.mapper.CookMapper;
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
@CacheConfig(cacheNames = "cook")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CookQueryService {

    @Autowired
    private CookRepository cookRepository;

    @Autowired
    private CookMapper cookMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CookDTO cook, Pageable pageable){
        Page<Cook> page = cookRepository.findAll(new Spec(cook),pageable);
        return PageUtil.toPage(page.map(cookMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CookDTO cook){
        return cookMapper.toDto(cookRepository.findAll(new Spec(cook)));
    }

    class Spec implements Specification<Cook> {

        private CookDTO cook;

        public Spec(CookDTO cook){
            this.cook = cook;
        }

        @Override
        public Predicate toPredicate(Root<Cook> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(cook.getName())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("name").as(String.class),"%"+cook.getName()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}