package me.zhengjie.modules.tvs.service.impl;

import me.zhengjie.modules.tvs.domain.Tvs;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.tvs.repository.TvsRepository;
import me.zhengjie.modules.tvs.service.TvsService;
import me.zhengjie.modules.tvs.service.dto.TvsDTO;
import me.zhengjie.modules.tvs.service.mapper.TvsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-07
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TvsServiceImpl implements TvsService {

    @Autowired
    private TvsRepository tvsRepository;

    @Autowired
    private TvsMapper tvsMapper;

    @Override
    public TvsDTO findById(Integer id) {
        Optional<Tvs> tvs = tvsRepository.findById(id);
        ValidationUtil.isNull(tvs,"Tvs","id",id);
        return tvsMapper.toDto(tvs.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TvsDTO create(Tvs resources) {
        return tvsMapper.toDto(tvsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Tvs resources) {
        Optional<Tvs> optionalTvs = tvsRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTvs,"Tvs","id",resources.getId());

        Tvs tvs = optionalTvs.get();
        // 此处需自己修改
        resources.setId(tvs.getId());
        tvsRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        tvsRepository.deleteById(id.intValue());
    }
}