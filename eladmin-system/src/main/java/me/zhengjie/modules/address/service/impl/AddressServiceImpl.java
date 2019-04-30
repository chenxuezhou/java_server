package me.zhengjie.modules.address.service.impl;

import me.zhengjie.modules.address.domain.Address;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.address.repository.AddressRepository;
import me.zhengjie.modules.address.service.AddressService;
import me.zhengjie.modules.address.service.dto.AddressDTO;
import me.zhengjie.modules.address.service.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-29
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressDTO findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        ValidationUtil.isNull(address,"Address","id",id);
        return addressMapper.toDto(address.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressDTO create(Address resources) {
        return addressMapper.toDto(addressRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Address resources) {
        Optional<Address> optionalAddress = addressRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAddress,"Address","id",resources.getId());

        Address address = optionalAddress.get();
        // 此处需自己修改
        resources.setId(address.getId());
        addressRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}