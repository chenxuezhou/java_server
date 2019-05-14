package me.zhengjie.modules.student.service.impl;

import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.student.repository.StudentRepository;
import me.zhengjie.modules.student.service.StudentService;
import me.zhengjie.modules.student.service.dto.StudentDTO;
import me.zhengjie.modules.student.service.mapper.StudentMapper;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentDTO findById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        ValidationUtil.isNull(student,"Student","id",id);
        return studentMapper.toDto(student.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StudentDTO create(Student resources) {
        return studentMapper.toDto(studentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Student resources) {
        Optional<Student> optionalStudent = studentRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalStudent,"Student","id",resources.getId());

        Student student = optionalStudent.get();
        // 此处需自己修改
        resources.setId(student.getId());
        studentRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}