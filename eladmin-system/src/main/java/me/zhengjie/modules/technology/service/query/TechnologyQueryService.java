package me.zhengjie.modules.technology.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.technology.domain.Technology;
import me.zhengjie.modules.technology.service.dto.TechnologyDTO;
import me.zhengjie.modules.technology.repository.TechnologyRepository;
import me.zhengjie.modules.technology.service.mapper.TechnologyMapper;
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
@CacheConfig(cacheNames = "technology")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TechnologyQueryService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private TechnologyMapper technologyMapper;

    /**
     * ��ҳ
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TechnologyDTO technology, Pageable pageable){
        Page<Technology> page = technologyRepository.findAll(new Spec(technology),pageable);
        return PageUtil.toPage(page.map(technologyMapper::toDto));
    }

    /**
    * ����ҳ
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TechnologyDTO technology){
        return technologyMapper.toDto(technologyRepository.findAll(new Spec(technology)));
    }

    class Spec implements Specification<Technology> {

        private TechnologyDTO technology;

        public Spec(TechnologyDTO technology){
            this.technology = technology;
        }

        @Override
        public Predicate toPredicate(Root<Technology> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(technology.getCreateTime())){
                    /**
                    * ��ȷ
                    */
                    list.add(cb.equal(root.get("create_time").as(Timestamp.class),technology.getCreateTime()));
                }
                if(!ObjectUtils.isEmpty(technology.getTitle())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+technology.getTitle()+"%"));
                }
                if(!ObjectUtils.isEmpty(technology.getNumber())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("number").as(String.class),"%"+technology.getNumber()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}