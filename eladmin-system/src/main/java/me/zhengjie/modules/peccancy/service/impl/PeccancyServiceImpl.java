package me.zhengjie.modules.peccancy.service.impl;

import me.zhengjie.modules.peccancy.domain.Peccancy;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.peccancy.repository.PeccancyRepository;
import me.zhengjie.modules.peccancy.service.PeccancyService;
import me.zhengjie.modules.peccancy.service.dto.PeccancyDTO;
import me.zhengjie.modules.peccancy.service.mapper.PeccancyMapper;
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
public class PeccancyServiceImpl implements PeccancyService {

    @Autowired
    private PeccancyRepository peccancyRepository;

    @Autowired
    private PeccancyMapper peccancyMapper;

    @Override
    public PeccancyDTO findById(Long id) {
        Optional<Peccancy> peccancy = peccancyRepository.findById(id);
        ValidationUtil.isNull(peccancy,"Peccancy","id",id);
        return peccancyMapper.toDto(peccancy.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PeccancyDTO create(Peccancy resources) {
        return peccancyMapper.toDto(peccancyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Peccancy resources) {
        Optional<Peccancy> optionalPeccancy = peccancyRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPeccancy,"Peccancy","id",resources.getId());

        Peccancy peccancy = optionalPeccancy.get();
        // 此处需自己修改
        resources.setId(peccancy.getId());
        peccancyRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        peccancyRepository.deleteById(id);
    }
}