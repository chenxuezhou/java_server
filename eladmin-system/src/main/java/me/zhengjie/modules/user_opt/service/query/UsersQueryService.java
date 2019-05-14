package me.zhengjie.modules.user_opt.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.user_opt.domain.Users;
import me.zhengjie.modules.user_opt.service.dto.UsersDTO;
import me.zhengjie.modules.user_opt.repository.UsersRepository;
import me.zhengjie.modules.user_opt.service.mapper.UsersMapper;
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
@CacheConfig(cacheNames = "users")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UsersQueryService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UsersDTO users, Pageable pageable){
        Page<Users> page = usersRepository.findAll(new Spec(users),pageable);
        return PageUtil.toPage(page.map(usersMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UsersDTO users){
        return usersMapper.toDto(usersRepository.findAll(new Spec(users)));
    }

    class Spec implements Specification<Users> {

        private UsersDTO users;

        public Spec(UsersDTO users){
            this.users = users;
        }

        @Override
        public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}