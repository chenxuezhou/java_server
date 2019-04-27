package me.zhengjie.modules.collect.service.impl;

import me.zhengjie.modules.collect.domain.Collect;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.collect.repository.CollectRepository;
import me.zhengjie.modules.collect.service.CollectService;
import me.zhengjie.modules.collect.service.dto.CollectDTO;
import me.zhengjie.modules.collect.service.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-27
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public CollectDTO findById(Long id) {
        Optional<Collect> collect = collectRepository.findById(id);
        ValidationUtil.isNull(collect,"Collect","id",id);
        return collectMapper.toDto(collect.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CollectDTO create(Collect resources) {
        return collectMapper.toDto(collectRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Collect resources) {
        Optional<Collect> optionalCollect = collectRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCollect,"Collect","id",resources.getId());

        Collect collect = optionalCollect.get();
        // 此处需自己修改
        resources.setId(collect.getId());
        collectRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        collectRepository.deleteById(id);
    }
}