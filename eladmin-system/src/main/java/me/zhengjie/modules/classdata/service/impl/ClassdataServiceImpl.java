package me.zhengjie.modules.classdata.service.impl;

import me.zhengjie.modules.classdata.domain.Classdata;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.classdata.repository.ClassdataRepository;
import me.zhengjie.modules.classdata.service.ClassdataService;
import me.zhengjie.modules.classdata.service.dto.ClassdataDTO;
import me.zhengjie.modules.classdata.service.mapper.ClassdataMapper;
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
public class ClassdataServiceImpl implements ClassdataService {

    @Autowired
    private ClassdataRepository classdataRepository;

    @Autowired
    private ClassdataMapper classdataMapper;

    @Override
    public ClassdataDTO findById(Integer id) {
        Optional<Classdata> classdata = classdataRepository.findById(id);
        ValidationUtil.isNull(classdata,"Classdata","id",id);
        return classdataMapper.toDto(classdata.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClassdataDTO create(Classdata resources) {
        return classdataMapper.toDto(classdataRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Classdata resources) {
        Optional<Classdata> optionalClassdata = classdataRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalClassdata,"Classdata","id",resources.getId());

        Classdata classdata = optionalClassdata.get();
        // 此处需自己修改
        resources.setId(classdata.getId());
        classdataRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        classdataRepository.deleteById(id.intValue());
    }
}