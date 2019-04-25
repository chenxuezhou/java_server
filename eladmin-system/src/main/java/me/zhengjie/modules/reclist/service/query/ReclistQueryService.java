package me.zhengjie.modules.reclist.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.reclist.domain.Reclist;
import me.zhengjie.modules.reclist.service.dto.ReclistDTO;
import me.zhengjie.modules.reclist.repository.ReclistRepository;
import me.zhengjie.modules.reclist.service.mapper.ReclistMapper;
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
@CacheConfig(cacheNames = "reclist")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ReclistQueryService {

    @Autowired
    private ReclistRepository reclistRepository;

    @Autowired
    private ReclistMapper reclistMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ReclistDTO reclist, Pageable pageable){
        Page<Reclist> page = reclistRepository.findAll(new Spec(reclist),pageable);
        return PageUtil.toPage(page.map(reclistMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ReclistDTO reclist){
        return reclistMapper.toDto(reclistRepository.findAll(new Spec(reclist)));
    }

    class Spec implements Specification<Reclist> {

        private ReclistDTO reclist;

        public Spec(ReclistDTO reclist){
            this.reclist = reclist;
        }

        @Override
        public Predicate toPredicate(Root<Reclist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(reclist.getTitle())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+reclist.getTitle()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}