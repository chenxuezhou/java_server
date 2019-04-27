package me.zhengjie.modules.sub.service.impl;

import me.zhengjie.modules.sub.domain.Sub;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.sub.repository.SubRepository;
import me.zhengjie.modules.sub.service.SubService;
import me.zhengjie.modules.sub.service.dto.SubDTO;
import me.zhengjie.modules.sub.service.mapper.SubMapper;
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
public class SubServiceImpl implements SubService {

    @Autowired
    private SubRepository subRepository;

    @Autowired
    private SubMapper subMapper;

    @Override
    public SubDTO findById(Long id) {
        Optional<Sub> sub = subRepository.findById(id);
        ValidationUtil.isNull(sub,"Sub","id",id);
        return subMapper.toDto(sub.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SubDTO create(Sub resources) {
        return subMapper.toDto(subRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Sub resources) {
        Optional<Sub> optionalSub = subRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalSub,"Sub","id",resources.getId());

        Sub sub = optionalSub.get();
        // 此处需自己修改
        resources.setId(sub.getId());
        subRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        subRepository.deleteById(id);
    }
}