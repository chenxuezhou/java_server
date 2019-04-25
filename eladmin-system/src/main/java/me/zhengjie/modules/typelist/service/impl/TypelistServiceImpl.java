package me.zhengjie.modules.typelist.service.impl;

import me.zhengjie.modules.typelist.domain.Typelist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.typelist.repository.TypelistRepository;
import me.zhengjie.modules.typelist.service.TypelistService;
import me.zhengjie.modules.typelist.service.dto.TypelistDTO;
import me.zhengjie.modules.typelist.service.mapper.TypelistMapper;
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
public class TypelistServiceImpl implements TypelistService {

    @Autowired
    private TypelistRepository typelistRepository;

    @Autowired
    private TypelistMapper typelistMapper;

    @Override
    public TypelistDTO findById(Integer id) {
        Optional<Typelist> typelist = typelistRepository.findById(id);
        ValidationUtil.isNull(typelist,"Typelist","id",id);
        return typelistMapper.toDto(typelist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TypelistDTO create(Typelist resources) {
        return typelistMapper.toDto(typelistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Typelist resources) {
        Optional<Typelist> optionalTypelist = typelistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTypelist,"Typelist","id",resources.getId());

        Typelist typelist = optionalTypelist.get();
        // 此处需自己修改
        resources.setId(typelist.getId());
        typelistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        typelistRepository.deleteById(id.intValue());
    }
}