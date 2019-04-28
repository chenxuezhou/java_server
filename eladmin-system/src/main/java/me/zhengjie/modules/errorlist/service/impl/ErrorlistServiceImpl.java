package me.zhengjie.modules.errorlist.service.impl;

import me.zhengjie.modules.errorlist.domain.Errorlist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.errorlist.repository.ErrorlistRepository;
import me.zhengjie.modules.errorlist.service.ErrorlistService;
import me.zhengjie.modules.errorlist.service.dto.ErrorlistDTO;
import me.zhengjie.modules.errorlist.service.mapper.ErrorlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-28
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ErrorlistServiceImpl implements ErrorlistService {

    @Autowired
    private ErrorlistRepository errorlistRepository;

    @Autowired
    private ErrorlistMapper errorlistMapper;

    @Override
    public ErrorlistDTO findById(Integer id) {
        Optional<Errorlist> errorlist = errorlistRepository.findById(id);
        ValidationUtil.isNull(errorlist,"Errorlist","id",id);
        return errorlistMapper.toDto(errorlist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ErrorlistDTO create(Errorlist resources) {
        return errorlistMapper.toDto(errorlistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Errorlist resources) {
        Optional<Errorlist> optionalErrorlist = errorlistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalErrorlist,"Errorlist","id",resources.getId());

        Errorlist errorlist = optionalErrorlist.get();
        // 此处需自己修改
        resources.setId(errorlist.getId());
        errorlistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        errorlistRepository.deleteById(id.intValue());
    }
}