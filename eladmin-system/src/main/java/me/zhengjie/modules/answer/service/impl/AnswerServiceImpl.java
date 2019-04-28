package me.zhengjie.modules.answer.service.impl;

import me.zhengjie.modules.answer.domain.Answer;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.answer.repository.AnswerRepository;
import me.zhengjie.modules.answer.service.AnswerService;
import me.zhengjie.modules.answer.service.dto.AnswerDTO;
import me.zhengjie.modules.answer.service.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-28
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public AnswerDTO findById(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        ValidationUtil.isNull(answer,"Answer","id",id);
        return answerMapper.toDto(answer.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AnswerDTO create(Answer resources) {
        return answerMapper.toDto(answerRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Answer resources) {
        Optional<Answer> optionalAnswer = answerRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAnswer,"Answer","id",resources.getId());

        Answer answer = optionalAnswer.get();
        // 此处需自己修改
        resources.setId(answer.getId());
        answerRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        answerRepository.deleteById(id);
    }
}