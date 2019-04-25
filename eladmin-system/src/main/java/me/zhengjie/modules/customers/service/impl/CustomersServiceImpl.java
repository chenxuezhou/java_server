package me.zhengjie.modules.customers.service.impl;

import me.zhengjie.modules.customers.domain.Customers;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.customers.repository.CustomersRepository;
import me.zhengjie.modules.customers.service.CustomersService;
import me.zhengjie.modules.customers.service.dto.CustomersDTO;
import me.zhengjie.modules.customers.service.mapper.CustomersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-24
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomersMapper customersMapper;

    @Override
    public CustomersDTO findById(Integer id) {
        Optional<Customers> customers = customersRepository.findById(id);
        ValidationUtil.isNull(customers,"Customers","id",id);
        return customersMapper.toDto(customers.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomersDTO create(Customers resources) {
        return customersMapper.toDto(customersRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Customers resources) {
        Optional<Customers> optionalCustomers = customersRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCustomers,"Customers","id",resources.getId());

        Customers customers = optionalCustomers.get();
        // 此处需自己修改
        resources.setId(customers.getId());
        customersRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        customersRepository.deleteById(id.intValue());
    }
}