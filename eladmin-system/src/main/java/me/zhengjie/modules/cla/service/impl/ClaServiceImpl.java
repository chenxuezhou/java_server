package me.zhengjie.modules.cla.service.impl;

import me.zhengjie.modules.cla.domain.Cla;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.cla.repository.ClaRepository;
import me.zhengjie.modules.cla.service.ClaService;
import me.zhengjie.modules.cla.service.dto.ClaDTO;
import me.zhengjie.modules.cla.service.mapper.ClaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClaServiceImpl implements ClaService {

    @Autowired
    private ClaRepository claRepository;

    @Autowired
    private ClaMapper claMapper;

    @Override
    public ClaDTO findById(Long id) {
        Optional<Cla> cla = claRepository.findById(id);
        ValidationUtil.isNull(cla,"Cla","id",id);
        return claMapper.toDto(cla.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClaDTO create(Cla resources) {
        return claMapper.toDto(claRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Cla resources) {
        Optional<Cla> optionalCla = claRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCla,"Cla","id",resources.getId());

        Cla cla = optionalCla.get();
        // 此处需自己修改
        resources.setId(cla.getId());
        claRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        claRepository.deleteById(id);
    }
}