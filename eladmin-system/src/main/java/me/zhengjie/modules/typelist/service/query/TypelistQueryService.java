package me.zhengjie.modules.typelist.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.typelist.domain.Typelist;
import me.zhengjie.modules.typelist.service.dto.TypelistDTO;
import me.zhengjie.modules.typelist.repository.TypelistRepository;
import me.zhengjie.modules.typelist.service.mapper.TypelistMapper;
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
@CacheConfig(cacheNames = "typelist")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TypelistQueryService {

    @Autowired
    private TypelistRepository typelistRepository;

    @Autowired
    private TypelistMapper typelistMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TypelistDTO typelist, Pageable pageable){
        Page<Typelist> page = typelistRepository.findAll(new Spec(typelist),pageable);
        return PageUtil.toPage(page.map(typelistMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TypelistDTO typelist){
        return typelistMapper.toDto(typelistRepository.findAll(new Spec(typelist)));
    }

    class Spec implements Specification<Typelist> {

        private TypelistDTO typelist;

        public Spec(TypelistDTO typelist){
            this.typelist = typelist;
        }

        @Override
        public Predicate toPredicate(Root<Typelist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(typelist.getText())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("text").as(String.class),"%"+typelist.getText()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}