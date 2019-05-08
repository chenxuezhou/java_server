package me.zhengjie.modules.userlog.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.userlog.domain.Userlog;
import me.zhengjie.modules.userlog.service.dto.UserlogDTO;
import me.zhengjie.modules.userlog.repository.UserlogRepository;
import me.zhengjie.modules.userlog.service.mapper.UserlogMapper;
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
@CacheConfig(cacheNames = "userlog")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserlogQueryService {

    @Autowired
    private UserlogRepository userlogRepository;

    @Autowired
    private UserlogMapper userlogMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UserlogDTO userlog, Pageable pageable){
        Page<Userlog> page = userlogRepository.findAll(new Spec(userlog),pageable);
        return PageUtil.toPage(page.map(userlogMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UserlogDTO userlog){
        return userlogMapper.toDto(userlogRepository.findAll(new Spec(userlog)));
    }

    class Spec implements Specification<Userlog> {

        private UserlogDTO userlog;

        public Spec(UserlogDTO userlog){
            this.userlog = userlog;
        }

        @Override
        public Predicate toPredicate(Root<Userlog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}