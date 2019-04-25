package me.zhengjie.modules.chapterlist.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.chapterlist.domain.Chapterlist;
import me.zhengjie.modules.chapterlist.service.dto.ChapterlistDTO;
import me.zhengjie.modules.chapterlist.repository.ChapterlistRepository;
import me.zhengjie.modules.chapterlist.service.mapper.ChapterlistMapper;
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
@CacheConfig(cacheNames = "chapterlist")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ChapterlistQueryService {

    @Autowired
    private ChapterlistRepository chapterlistRepository;

    @Autowired
    private ChapterlistMapper chapterlistMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ChapterlistDTO chapterlist, Pageable pageable){
        Page<Chapterlist> page = chapterlistRepository.findAll(new Spec(chapterlist),pageable);
        return PageUtil.toPage(page.map(chapterlistMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ChapterlistDTO chapterlist){
        return chapterlistMapper.toDto(chapterlistRepository.findAll(new Spec(chapterlist)));
    }

    class Spec implements Specification<Chapterlist> {

        private ChapterlistDTO chapterlist;

        public Spec(ChapterlistDTO chapterlist){
            this.chapterlist = chapterlist;
        }

        @Override
        public Predicate toPredicate(Root<Chapterlist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(chapterlist.getTitle())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+chapterlist.getTitle()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}