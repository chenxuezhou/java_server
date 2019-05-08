package me.zhengjie.modules.preview.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.preview.domain.Preview;
import me.zhengjie.modules.preview.service.dto.PreviewDTO;
import me.zhengjie.modules.preview.repository.PreviewRepository;
import me.zhengjie.modules.preview.service.mapper.PreviewMapper;
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
@CacheConfig(cacheNames = "preview")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PreviewQueryService {

    @Autowired
    private PreviewRepository previewRepository;

    @Autowired
    private PreviewMapper previewMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PreviewDTO preview, Pageable pageable){
        Page<Preview> page = previewRepository.findAll(new Spec(preview),pageable);
        return PageUtil.toPage(page.map(previewMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PreviewDTO preview){
        return previewMapper.toDto(previewRepository.findAll(new Spec(preview)));
    }

    class Spec implements Specification<Preview> {

        private PreviewDTO preview;

        public Spec(PreviewDTO preview){
            this.preview = preview;
        }

        @Override
        public Predicate toPredicate(Root<Preview> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(preview.getTitle())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+preview.getTitle()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}