package me.zhengjie.modules.mark.service.impl;

import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.mark.repository.MarkRepository;
import me.zhengjie.modules.mark.service.MarkService;
import me.zhengjie.modules.mark.service.dto.MarkDTO;
import me.zhengjie.modules.mark.service.mapper.MarkMapper;
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
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private MarkMapper markMapper;

    @Override
    public MarkDTO findById(String id) {
        Optional<Mark> mark = markRepository.findById(id);
        ValidationUtil.isNull(mark,"Mark","id",id);
        return markMapper.toDto(mark.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MarkDTO create(Mark resources) {
        return markMapper.toDto(markRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Mark resources) {
        Optional<Mark> optionalMark = markRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMark,"Mark","id",resources.getId());

        Mark mark = optionalMark.get();
        // 此处需自己修改
        resources.setId(mark.getId());
        markRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        markRepository.deleteById(id);
    }
}