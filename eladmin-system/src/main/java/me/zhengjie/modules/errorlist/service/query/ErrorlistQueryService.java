package me.zhengjie.modules.errorlist.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.errorlist.domain.Errorlist;
import me.zhengjie.modules.errorlist.service.dto.ErrorlistDTO;
import me.zhengjie.modules.errorlist.repository.ErrorlistRepository;
import me.zhengjie.modules.errorlist.service.mapper.ErrorlistMapper;
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
@CacheConfig(cacheNames = "errorlist")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ErrorlistQueryService {

    @Autowired
    private ErrorlistRepository errorlistRepository;

    @Autowired
    private ErrorlistMapper errorlistMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ErrorlistDTO errorlist, Pageable pageable){
        Page<Errorlist> page = errorlistRepository.findAll(new Spec(errorlist),pageable);
        return PageUtil.toPage(page.map(errorlistMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ErrorlistDTO errorlist){
        return errorlistMapper.toDto(errorlistRepository.findAll(new Spec(errorlist)));
    }

    class Spec implements Specification<Errorlist> {

        private ErrorlistDTO errorlist;

        public Spec(ErrorlistDTO errorlist){
            this.errorlist = errorlist;
        }

        @Override
        public Predicate toPredicate(Root<Errorlist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(errorlist.getContent())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("content").as(String.class),"%"+errorlist.getContent()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}