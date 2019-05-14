package me.zhengjie.modules.teacher.service.impl;

import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.teacher.repository.TeacherRepository;
import me.zhengjie.modules.teacher.service.TeacherService;
import me.zhengjie.modules.teacher.service.dto.TeacherDTO;
import me.zhengjie.modules.teacher.service.mapper.TeacherMapper;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public TeacherDTO findById(String id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        ValidationUtil.isNull(teacher,"Teacher","id",id);
        return teacherMapper.toDto(teacher.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TeacherDTO create(Teacher resources) {
        return teacherMapper.toDto(teacherRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Teacher resources) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTeacher,"Teacher","id",resources.getId());

        Teacher teacher = optionalTeacher.get();
        // 此处需自己修改
        resources.setId(teacher.getId());
        teacherRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        teacherRepository.deleteById(id);
    }
}