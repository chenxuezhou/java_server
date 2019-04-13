package me.zhengjie.modules.technology.service.impl;

import me.zhengjie.modules.technology.domain.Technology;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.technology.repository.TechnologyRepository;
import me.zhengjie.modules.technology.service.TechnologyService;
import me.zhengjie.modules.technology.service.dto.TechnologyDTO;
import me.zhengjie.modules.technology.service.mapper.TechnologyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-03-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private TechnologyMapper technologyMapper;

    @Override
    public TechnologyDTO findById(Long id) {
        Optional<Technology> technology = technologyRepository.findById(id);
        ValidationUtil.isNull(technology,"Technology","id",id);
        return technologyMapper.toDto(technology.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TechnologyDTO create(Technology resources) {
        return technologyMapper.toDto(technologyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Technology resources) {
        Optional<Technology> optionalTechnology = technologyRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTechnology,"Technology","id",resources.getId());

        Technology technology = optionalTechnology.get();
        // 此处需自己修改
        resources.setId(technology.getId());
        technologyRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        technologyRepository.deleteById(id);
    }
}