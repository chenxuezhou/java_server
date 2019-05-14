package me.zhengjie.modules.pub_house.service.impl;

import me.zhengjie.modules.pub_house.domain.PubHouse;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.pub_house.repository.PubHouseRepository;
import me.zhengjie.modules.pub_house.service.PubHouseService;
import me.zhengjie.modules.pub_house.service.dto.PubHouseDTO;
import me.zhengjie.modules.pub_house.service.mapper.PubHouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-14
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PubHouseServiceImpl implements PubHouseService {

    @Autowired
    private PubHouseRepository pubHouseRepository;

    @Autowired
    private PubHouseMapper pubHouseMapper;

    @Override
    public PubHouseDTO findById(Long id) {
        Optional<PubHouse> pubHouse = pubHouseRepository.findById(id);
        ValidationUtil.isNull(pubHouse,"PubHouse","id",id);
        return pubHouseMapper.toDto(pubHouse.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PubHouseDTO create(PubHouse resources) {
        return pubHouseMapper.toDto(pubHouseRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PubHouse resources) {
        Optional<PubHouse> optionalPubHouse = pubHouseRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPubHouse,"PubHouse","id",resources.getId());

        PubHouse pubHouse = optionalPubHouse.get();
        // 此处需自己修改
        resources.setId(pubHouse.getId());
        pubHouseRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        pubHouseRepository.deleteById(id);
    }
}