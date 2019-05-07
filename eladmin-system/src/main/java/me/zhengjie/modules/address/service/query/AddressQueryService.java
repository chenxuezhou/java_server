package me.zhengjie.modules.address.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.address.domain.Address;
import me.zhengjie.modules.address.service.dto.AddressDTO;
import me.zhengjie.modules.address.repository.AddressRepository;
import me.zhengjie.modules.address.service.mapper.AddressMapper;
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
@CacheConfig(cacheNames = "address")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AddressQueryService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AddressDTO address, Pageable pageable){
        Page<Address> page = addressRepository.findAll(new Spec(address),pageable);
        return PageUtil.toPage(page.map(addressMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AddressDTO address){
        return addressMapper.toDto(addressRepository.findAll(new Spec(address)));
    }

    class Spec implements Specification<Address> {

        private AddressDTO address;

        public Spec(AddressDTO address){
            this.address = address;
        }

        @Override
        public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();
            if(!ObjectUtils.isEmpty(address.getCusId())){
                /**
                 * 模糊
                 */
                list.add(cb.equal(root.get("cusId").as(String.class),address.getCusId()));
            }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}