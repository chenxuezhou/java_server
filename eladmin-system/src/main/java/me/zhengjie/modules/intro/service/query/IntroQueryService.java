package me.zhengjie.modules.intro.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.intro.domain.Intro;
import me.zhengjie.modules.intro.service.dto.IntroDTO;
import me.zhengjie.modules.intro.repository.IntroRepository;
import me.zhengjie.modules.intro.service.mapper.IntroMapper;
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
@CacheConfig(cacheNames = "intro")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class IntroQueryService {

    @Autowired
    private IntroRepository introRepository;

    @Autowired
    private IntroMapper introMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(IntroDTO intro, Pageable pageable){
        Page<Intro> page = introRepository.findAll(new Spec(intro),pageable);
        return PageUtil.toPage(page.map(introMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(IntroDTO intro){
        return introMapper.toDto(introRepository.findAll(new Spec(intro)));
    }

    class Spec implements Specification<Intro> {

        private IntroDTO intro;

        public Spec(IntroDTO intro){
            this.intro = intro;
        }

        @Override
        public Predicate toPredicate(Root<Intro> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(intro.getLevel())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("level").as(String.class),intro.getLevel()));
                }
                if(!ObjectUtils.isEmpty(intro.getRate())){
                    /**
                    * 精确
                    */
                    list.add(cb.equal(root.get("rate").as(Double.class),intro.getRate()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}