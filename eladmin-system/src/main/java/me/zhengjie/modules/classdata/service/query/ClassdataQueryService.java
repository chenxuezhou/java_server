package me.zhengjie.modules.classdata.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.classdata.domain.Classdata;
import me.zhengjie.modules.classdata.service.dto.ClassdataDTO;
import me.zhengjie.modules.classdata.repository.ClassdataRepository;
import me.zhengjie.modules.classdata.service.mapper.ClassdataMapper;
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
@CacheConfig(cacheNames = "classdata")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClassdataQueryService {

    @Autowired
    private ClassdataRepository classdataRepository;

    @Autowired
    private ClassdataMapper classdataMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ClassdataDTO classdata, Pageable pageable){
        Page<Classdata> page = classdataRepository.findAll(new Spec(classdata),pageable);
        return PageUtil.toPage(page.map(classdataMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ClassdataDTO classdata){
        return classdataMapper.toDto(classdataRepository.findAll(new Spec(classdata)));
    }

    class Spec implements Specification<Classdata> {

        private ClassdataDTO classdata;

        public Spec(ClassdataDTO classdata){
            this.classdata = classdata;
        }

        @Override
        public Predicate toPredicate(Root<Classdata> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(classdata.getTitle())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+classdata.getTitle()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}