package me.zhengjie.modules.sub.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.sub.domain.Sub;
import me.zhengjie.modules.sub.service.dto.SubDTO;
import me.zhengjie.modules.sub.repository.SubRepository;
import me.zhengjie.modules.sub.service.mapper.SubMapper;
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
@CacheConfig(cacheNames = "sub")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SubQueryService {

    @Autowired
    private SubRepository subRepository;

    @Autowired
    private SubMapper subMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SubDTO sub, Pageable pageable){
        Page<Sub> page = subRepository.findAll(new Spec(sub),pageable);
        return PageUtil.toPage(page.map(subMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(SubDTO sub){
        return subMapper.toDto(subRepository.findAll(new Spec(sub)));
    }

    class Spec implements Specification<Sub> {

        private SubDTO sub;

        public Spec(SubDTO sub){
            this.sub = sub;
        }

        @Override
        public Predicate toPredicate(Root<Sub> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();
            if(!ObjectUtils.isEmpty(sub.getUserId())){
                /**
                 * ��ȷ
                 */
                list.add(cb.equal(root.get("userId").as(String.class),sub.getUserId()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}