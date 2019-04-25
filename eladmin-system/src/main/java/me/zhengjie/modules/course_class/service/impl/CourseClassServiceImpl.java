package me.zhengjie.modules.course_class.service.impl;

import me.zhengjie.modules.course_class.domain.CourseClass;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.course_class.repository.CourseClassRepository;
import me.zhengjie.modules.course_class.service.CourseClassService;
import me.zhengjie.modules.course_class.service.dto.CourseClassDTO;
import me.zhengjie.modules.course_class.service.mapper.CourseClassMapper;
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
public class CourseClassServiceImpl implements CourseClassService {

    @Autowired
    private CourseClassRepository courseClassRepository;

    @Autowired
    private CourseClassMapper courseClassMapper;

    @Override
    public CourseClassDTO findById(Integer id) {
        Optional<CourseClass> courseClass = courseClassRepository.findById(id);
        ValidationUtil.isNull(courseClass,"CourseClass","id",id);
        return courseClassMapper.toDto(courseClass.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseClassDTO create(CourseClass resources) {
        return courseClassMapper.toDto(courseClassRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CourseClass resources) {
        Optional<CourseClass> optionalCourseClass = courseClassRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalCourseClass,"CourseClass","id",resources.getId());

        CourseClass courseClass = optionalCourseClass.get();
        // 此处需自己修改
        resources.setId(courseClass.getId());
        courseClassRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        courseClassRepository.deleteById(id.intValue());
    }
}