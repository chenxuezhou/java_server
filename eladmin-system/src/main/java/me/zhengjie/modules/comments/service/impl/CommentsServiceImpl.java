package me.zhengjie.modules.comments.service.impl;

import me.zhengjie.modules.comments.domain.Comments;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.comments.repository.CommentsRepository;
import me.zhengjie.modules.comments.service.CommentsService;
import me.zhengjie.modules.comments.service.dto.CommentsDTO;
import me.zhengjie.modules.comments.service.mapper.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-05-07
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public CommentsDTO findById(Integer id) {
        Optional<Comments> comments = commentsRepository.findById(id);
        ValidationUtil.isNull(comments,"Comments","id",id);
        return commentsMapper.toDto(comments.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentsDTO create(Comments resources) {
        return commentsMapper.toDto(commentsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Comments resources) {
        Optional<Comments> optionalComments = commentsRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalComments,"Comments","id",resources.getId());

        Comments comments = optionalComments.get();
        // 此处需自己修改
        resources.setId(comments.getId());
        commentsRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        commentsRepository.deleteById(id.intValue());
    }
}