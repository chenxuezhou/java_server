package me.zhengjie.modules.courseschedule.service.impl;

import me.zhengjie.modules.courseschedule.domain.Courseschedule;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.courseschedule.repository.CoursescheduleRepository;
import me.zhengjie.modules.courseschedule.service.CoursescheduleService;
import me.zhengjie.modules.courseschedule.service.dto.CoursescheduleDTO;
import me.zhengjie.modules.courseschedule.service.mapper.CoursescheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-14
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CoursescheduleServiceImpl implements CoursescheduleService {

    @Autowired
    private CoursescheduleRepository coursescheduleRepository;

    @Autowired
    private CoursescheduleMapper coursescheduleMapper;

    @Override
    public CoursescheduleDTO findById(String id) {
        Optional<Courseschedule> courseschedule = coursescheduleRepository.findById(id);
        ValidationUtil.isNull(courseschedule,"Courseschedule","id",id);
        return coursescheduleMapper.toDto(courseschedule.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CoursescheduleDTO create(Courseschedule resources) {
        return coursescheduleMapper.toDto(coursescheduleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Courseschedule resources) {
        Optional<Courseschedule> optionalCourseschedule = coursescheduleRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCourseschedule,"Courseschedule","id",resources.getId());

        Courseschedule courseschedule = optionalCourseschedule.get();
        // 此处需自己修改
        resources.setId(courseschedule.getId());
        coursescheduleRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        coursescheduleRepository.deleteById(id);
    }
}