package me.zhengjie.modules.comments.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.comments.domain.Comments;
import me.zhengjie.modules.comments.service.dto.CommentsDTO;
import me.zhengjie.modules.comments.repository.CommentsRepository;
import me.zhengjie.modules.comments.service.mapper.CommentsMapper;
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
@CacheConfig(cacheNames = "comments")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CommentsQueryService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsMapper commentsMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CommentsDTO comments, Pageable pageable){
        Page<Comments> page = commentsRepository.findAll(new Spec(comments),pageable);
        return PageUtil.toPage(page.map(commentsMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CommentsDTO comments){
        return commentsMapper.toDto(commentsRepository.findAll(new Spec(comments)));
    }

    class Spec implements Specification<Comments> {

        private CommentsDTO comments;

        public Spec(CommentsDTO comments){
            this.comments = comments;
        }

        @Override
        public Predicate toPredicate(Root<Comments> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(comments.getContent())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("content").as(String.class),"%"+comments.getContent()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}