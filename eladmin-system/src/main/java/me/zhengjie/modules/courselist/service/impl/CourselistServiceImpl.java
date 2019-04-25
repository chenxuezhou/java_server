package me.zhengjie.modules.courselist.service.impl;

import me.zhengjie.modules.courselist.domain.Courselist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.courselist.repository.CourselistRepository;
import me.zhengjie.modules.courselist.service.CourselistService;
import me.zhengjie.modules.courselist.service.dto.CourselistDTO;
import me.zhengjie.modules.courselist.service.mapper.CourselistMapper;
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
public class CourselistServiceImpl implements CourselistService {

    @Autowired
    private CourselistRepository courselistRepository;

    @Autowired
    private CourselistMapper courselistMapper;

    @Override
    public CourselistDTO findById(Integer id) {
        Optional<Courselist> courselist = courselistRepository.findById(id);
        ValidationUtil.isNull(courselist,"Courselist","id",id);
        return courselistMapper.toDto(courselist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourselistDTO create(Courselist resources) {
        return courselistMapper.toDto(courselistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Courselist resources) {
        Optional<Courselist> optionalCourselist = courselistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCourselist,"Courselist","id",resources.getId());

        Courselist courselist = optionalCourselist.get();
        // 此处需自己修改
        resources.setId(courselist.getId());
        courselistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        courselistRepository.deleteById(id.intValue());
    }
}