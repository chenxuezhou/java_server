package me.zhengjie.modules.mark.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.modules.mark.service.dto.MarkDTO;
import me.zhengjie.modules.mark.repository.MarkRepository;
import me.zhengjie.modules.mark.service.mapper.MarkMapper;
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
@CacheConfig(cacheNames = "mark")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MarkQueryService {

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private MarkMapper markMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MarkDTO mark, Pageable pageable){
        Page<Mark> page = markRepository.findAll(new Spec(mark),pageable);
        return PageUtil.toPage(page.map(markMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MarkDTO mark){
        return markMapper.toDto(markRepository.findAll(new Spec(mark)));
    }

    class Spec implements Specification<Mark> {

        private MarkDTO mark;

        public Spec(MarkDTO mark){
            this.mark = mark;
        }

        @Override
        public Predicate toPredicate(Root<Mark> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}