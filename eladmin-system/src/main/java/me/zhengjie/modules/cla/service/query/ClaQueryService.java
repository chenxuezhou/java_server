package me.zhengjie.modules.cla.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.cla.domain.Cla;
import me.zhengjie.modules.cla.service.dto.ClaDTO;
import me.zhengjie.modules.cla.repository.ClaRepository;
import me.zhengjie.modules.cla.service.mapper.ClaMapper;
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
@CacheConfig(cacheNames = "cla")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClaQueryService {

    @Autowired
    private ClaRepository claRepository;

    @Autowired
    private ClaMapper claMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ClaDTO cla, Pageable pageable){
        Page<Cla> page = claRepository.findAll(new Spec(cla),pageable);
        return PageUtil.toPage(page.map(claMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ClaDTO cla){
        return claMapper.toDto(claRepository.findAll(new Spec(cla)));
    }

    class Spec implements Specification<Cla> {

        private ClaDTO cla;

        public Spec(ClaDTO cla){
            this.cla = cla;
        }

        @Override
        public Predicate toPredicate(Root<Cla> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(cla.getName())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("name").as(String.class),"%"+cla.getName()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}