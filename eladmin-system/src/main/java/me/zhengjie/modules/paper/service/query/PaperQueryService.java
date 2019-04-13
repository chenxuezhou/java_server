package me.zhengjie.modules.paper.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.paper.domain.Paper;
import me.zhengjie.modules.paper.service.dto.PaperDTO;
import me.zhengjie.modules.paper.repository.PaperRepository;
import me.zhengjie.modules.paper.service.mapper.PaperMapper;
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
@CacheConfig(cacheNames = "paper")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PaperQueryService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperMapper paperMapper;

    /**
     * ��ҳ
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PaperDTO paper, Pageable pageable){
        Page<Paper> page = paperRepository.findAll(new Spec(paper),pageable);
        return PageUtil.toPage(page.map(paperMapper::toDto));
    }

    /**
    * ����ҳ
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(PaperDTO paper){
        return paperMapper.toDto(paperRepository.findAll(new Spec(paper)));
    }

    class Spec implements Specification<Paper> {

        private PaperDTO paper;

        public Spec(PaperDTO paper){
            this.paper = paper;
        }

        @Override
        public Predicate toPredicate(Root<Paper> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(paper.getCreateTime())){
                    /**
                    * ��ȷ
                    */
                    list.add(cb.equal(root.get("create_time").as(Timestamp.class),paper.getCreateTime()));
                }
                if(!ObjectUtils.isEmpty(paper.getTitle())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("title").as(String.class),"%"+paper.getTitle()+"%"));
                }
                if(!ObjectUtils.isEmpty(paper.getNumber())){
                    /**
                    * ģ��
                    */
                    list.add(cb.like(root.get("number").as(String.class),"%"+paper.getNumber()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}