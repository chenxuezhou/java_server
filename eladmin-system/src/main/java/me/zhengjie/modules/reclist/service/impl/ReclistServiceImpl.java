package me.zhengjie.modules.reclist.service.impl;

import me.zhengjie.modules.reclist.domain.Reclist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.reclist.repository.ReclistRepository;
import me.zhengjie.modules.reclist.service.ReclistService;
import me.zhengjie.modules.reclist.service.dto.ReclistDTO;
import me.zhengjie.modules.reclist.service.mapper.ReclistMapper;
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
public class ReclistServiceImpl implements ReclistService {

    @Autowired
    private ReclistRepository reclistRepository;

    @Autowired
    private ReclistMapper reclistMapper;

    @Override
    public ReclistDTO findById(Integer id) {
        Optional<Reclist> reclist = reclistRepository.findById(id);
        ValidationUtil.isNull(reclist,"Reclist","id",id);
        return reclistMapper.toDto(reclist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReclistDTO create(Reclist resources) {
        return reclistMapper.toDto(reclistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Reclist resources) {
        Optional<Reclist> optionalReclist = reclistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalReclist,"Reclist","id",resources.getId());

        Reclist reclist = optionalReclist.get();
        // 此处需自己修改
        resources.setId(reclist.getId());
        reclistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        reclistRepository.deleteById(id.intValue());
    }
}