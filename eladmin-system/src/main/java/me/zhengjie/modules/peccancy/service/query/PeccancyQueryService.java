package me.zhengjie.modules.peccancy.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.peccancy.domain.Peccancy;
import me.zhengjie.modules.peccancy.service.dto.PeccancyDTO;
import me.zhengjie.modules.peccancy.repository.PeccancyRepository;
import me.zhengjie.modules.peccancy.service.mapper.PeccancyMapper;
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
@CacheConfig(cacheNames = "peccancy")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PeccancyQueryService {

    @Autowired
    private PeccancyRepository peccancyRepository;

    @Autowired
    private PeccancyMapper peccancyMapper;

    /**
     * ��ҳ
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PeccancyDTO peccancy, Pageable pageable){
        Page<Peccancy> page = peccancyRepository.findAll(new Spec(peccancy),pageable);
        return PageUtil.toPage(page.map(peccancyMapper::toDto));
    }

    /**
    * ����ҳ
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PeccancyDTO peccancy){
        return peccancyMapper.toDto(peccancyRepository.findAll(new Spec(peccancy)));
    }

    class Spec implements Specification<Peccancy> {

        private PeccancyDTO peccancy;

        public Spec(PeccancyDTO peccancy){
            this.peccancy = peccancy;
        }

        @Override
        public Predicate toPredicate(Root<Peccancy> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(peccancy.getCreateTime())){
                    /**
                    * ��ȷ
                    */
                    list.add(cb.equal(root.get("create_time").as(Timestamp.class),peccancy.getCreateTime()));
                }
                if(!ObjectUtils.isEmpty(peccancy.getTitle())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+peccancy.getTitle()+"%"));
                }
                if(!ObjectUtils.isEmpty(peccancy.getNumber())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("number").as(String.class),"%"+peccancy.getNumber()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}