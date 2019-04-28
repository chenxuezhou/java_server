package me.zhengjie.modules.testlist.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.testlist.domain.Testlist;
import me.zhengjie.modules.testlist.service.dto.TestlistDTO;
import me.zhengjie.modules.testlist.repository.TestlistRepository;
import me.zhengjie.modules.testlist.service.mapper.TestlistMapper;
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
@CacheConfig(cacheNames = "testlist")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TestlistQueryService {

    @Autowired
    private TestlistRepository testlistRepository;

    @Autowired
    private TestlistMapper testlistMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TestlistDTO testlist, Pageable pageable){
        Page<Testlist> page = testlistRepository.findAll(new Spec(testlist),pageable);
        return PageUtil.toPage(page.map(testlistMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TestlistDTO testlist){
        return testlistMapper.toDto(testlistRepository.findAll(new Spec(testlist)));
    }

    class Spec implements Specification<Testlist> {

        private TestlistDTO testlist;

        public Spec(TestlistDTO testlist){
            this.testlist = testlist;
        }

        @Override
        public Predicate toPredicate(Root<Testlist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(testlist.getContent())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("content").as(String.class),"%"+testlist.getContent()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}