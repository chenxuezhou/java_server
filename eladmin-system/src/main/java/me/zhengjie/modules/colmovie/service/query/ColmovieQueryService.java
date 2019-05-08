package me.zhengjie.modules.colmovie.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.colmovie.domain.Colmovie;
import me.zhengjie.modules.colmovie.service.dto.ColmovieDTO;
import me.zhengjie.modules.colmovie.repository.ColmovieRepository;
import me.zhengjie.modules.colmovie.service.mapper.ColmovieMapper;
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
@CacheConfig(cacheNames = "colmovie")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ColmovieQueryService {

    @Autowired
    private ColmovieRepository colmovieRepository;

    @Autowired
    private ColmovieMapper colmovieMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ColmovieDTO colmovie, Pageable pageable){
        Page<Colmovie> page = colmovieRepository.findAll(new Spec(colmovie),pageable);
        return PageUtil.toPage(page.map(colmovieMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ColmovieDTO colmovie){
        return colmovieMapper.toDto(colmovieRepository.findAll(new Spec(colmovie)));
    }

    class Spec implements Specification<Colmovie> {

        private ColmovieDTO colmovie;

        public Spec(ColmovieDTO colmovie){
            this.colmovie = colmovie;
        }

        @Override
        public Predicate toPredicate(Root<Colmovie> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}