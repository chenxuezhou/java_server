package me.zhengjie.modules.chapterlist.service.impl;

import me.zhengjie.modules.chapterlist.domain.Chapterlist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.chapterlist.repository.ChapterlistRepository;
import me.zhengjie.modules.chapterlist.service.ChapterlistService;
import me.zhengjie.modules.chapterlist.service.dto.ChapterlistDTO;
import me.zhengjie.modules.chapterlist.service.mapper.ChapterlistMapper;
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
public class ChapterlistServiceImpl implements ChapterlistService {

    @Autowired
    private ChapterlistRepository chapterlistRepository;

    @Autowired
    private ChapterlistMapper chapterlistMapper;

    @Override
    public ChapterlistDTO findById(Integer id) {
        Optional<Chapterlist> chapterlist = chapterlistRepository.findById(id);
        ValidationUtil.isNull(chapterlist,"Chapterlist","id",id);
        return chapterlistMapper.toDto(chapterlist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChapterlistDTO create(Chapterlist resources) {
        return chapterlistMapper.toDto(chapterlistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Chapterlist resources) {
        Optional<Chapterlist> optionalChapterlist = chapterlistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalChapterlist,"Chapterlist","id",resources.getId());

        Chapterlist chapterlist = optionalChapterlist.get();
        // 此处需自己修改
        resources.setId(chapterlist.getId());
        chapterlistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        chapterlistRepository.deleteById(id.intValue());
    }
}