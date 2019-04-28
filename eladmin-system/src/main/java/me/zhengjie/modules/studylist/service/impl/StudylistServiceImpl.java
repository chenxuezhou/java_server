package me.zhengjie.modules.studylist.service.impl;

import me.zhengjie.modules.studylist.domain.Studylist;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.studylist.repository.StudylistRepository;
import me.zhengjie.modules.studylist.service.StudylistService;
import me.zhengjie.modules.studylist.service.dto.StudylistDTO;
import me.zhengjie.modules.studylist.service.mapper.StudylistMapper;
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
public class StudylistServiceImpl implements StudylistService {

    @Autowired
    private StudylistRepository studylistRepository;

    @Autowired
    private StudylistMapper studylistMapper;

    @Override
    public StudylistDTO findById(Integer id) {
        Optional<Studylist> studylist = studylistRepository.findById(id);
        ValidationUtil.isNull(studylist,"Studylist","id",id);
        return studylistMapper.toDto(studylist.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StudylistDTO create(Studylist resources) {
        return studylistMapper.toDto(studylistRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Studylist resources) {
        Optional<Studylist> optionalStudylist = studylistRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalStudylist,"Studylist","id",resources.getId());

        Studylist studylist = optionalStudylist.get();
        // 此处需自己修改
        resources.setId(studylist.getId());
        studylistRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        studylistRepository.deleteById(id.intValue());
    }
}