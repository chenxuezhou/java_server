package me.zhengjie.modules.customers.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.customers.domain.Customers;
import me.zhengjie.modules.customers.service.dto.CustomersDTO;
import me.zhengjie.modules.customers.repository.CustomersRepository;
import me.zhengjie.modules.customers.service.mapper.CustomersMapper;
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
@CacheConfig(cacheNames = "customers")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CustomersQueryService {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomersMapper customersMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CustomersDTO customers, Pageable pageable){
        Page<Customers> page = customersRepository.findAll(new Spec(customers),pageable);
        return PageUtil.toPage(page.map(customersMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CustomersDTO customers){
        return customersMapper.toDto(customersRepository.findAll(new Spec(customers)));
    }

    class Spec implements Specification<Customers> {

        private CustomersDTO customers;

        public Spec(CustomersDTO customers){
            this.customers = customers;
        }

        @Override
        public Predicate toPredicate(Root<Customers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(customers.getName())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("name").as(String.class),"%"+customers.getName()+"%"));
                }
                if(!ObjectUtils.isEmpty(customers.getTel())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("tel").as(String.class),"%"+customers.getTel()+"%"));
                }
                if(!ObjectUtils.isEmpty(customers.getPassword())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("password").as(String.class),"%"+customers.getPassword()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}