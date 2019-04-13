package me.zhengjie.modules.paper.service.impl;

import me.zhengjie.modules.paper.domain.Paper;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.paper.repository.PaperRepository;
import me.zhengjie.modules.paper.service.PaperService;
import me.zhengjie.modules.paper.service.dto.PaperDTO;
import me.zhengjie.modules.paper.service.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-03-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperMapper paperMapper;

    @Override
    public PaperDTO findById(Long id) {
        Optional<Paper> paper = paperRepository.findById(id);
        ValidationUtil.isNull(paper,"Paper","id",id);
        return paperMapper.toDto(paper.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaperDTO create(Paper resources) {
        return paperMapper.toDto(paperRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Paper resources) {
        Optional<Paper> optionalPaper = paperRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPaper,"Paper","id",resources.getId());

        Paper paper = optionalPaper.get();
        // 此处需自己修改
        resources.setId(paper.getId());
        paperRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        paperRepository.deleteById(id);
    }
}