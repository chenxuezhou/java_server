package me.zhengjie.modules.cook.service.impl;

import me.zhengjie.modules.cook.domain.Cook;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.cook.repository.CookRepository;
import me.zhengjie.modules.cook.service.CookService;
import me.zhengjie.modules.cook.service.dto.CookDTO;
import me.zhengjie.modules.cook.service.mapper.CookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-30
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CookServiceImpl implements CookService {

    @Autowired
    private CookRepository cookRepository;

    @Autowired
    private CookMapper cookMapper;

    @Override
    public CookDTO findById(Long id) {
        Optional<Cook> cook = cookRepository.findById(id);
        ValidationUtil.isNull(cook,"Cook","id",id);
        return cookMapper.toDto(cook.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CookDTO create(Cook resources) {
        return cookMapper.toDto(cookRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Cook resources) {
        Optional<Cook> optionalCook = cookRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCook,"Cook","id",resources.getId());

        Cook cook = optionalCook.get();
        // 此处需自己修改
        resources.setId(cook.getId());
        cookRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        cookRepository.deleteById(id);
    }
}