package me.zhengjie.modules.exam.service.impl;

import me.zhengjie.modules.exam.domain.Exam;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.exam.repository.ExamRepository;
import me.zhengjie.modules.exam.service.ExamService;
import me.zhengjie.modules.exam.service.dto.ExamDTO;
import me.zhengjie.modules.exam.service.mapper.ExamMapper;
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
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamMapper examMapper;

    @Override
    public ExamDTO findById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        ValidationUtil.isNull(exam,"Exam","id",id);
        return examMapper.toDto(exam.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamDTO create(Exam resources) {
        return examMapper.toDto(examRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Exam resources) {
        Optional<Exam> optionalExam = examRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalExam,"Exam","id",resources.getId());

        Exam exam = optionalExam.get();
        // 此处需自己修改
        resources.setId(exam.getId());
        examRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}