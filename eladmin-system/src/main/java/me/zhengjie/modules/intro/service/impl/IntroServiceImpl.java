package me.zhengjie.modules.intro.service.impl;

import me.zhengjie.modules.intro.domain.Intro;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.intro.repository.IntroRepository;
import me.zhengjie.modules.intro.service.IntroService;
import me.zhengjie.modules.intro.service.dto.IntroDTO;
import me.zhengjie.modules.intro.service.mapper.IntroMapper;
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
public class IntroServiceImpl implements IntroService {

    @Autowired
    private IntroRepository introRepository;

    @Autowired
    private IntroMapper introMapper;

    @Override
    public IntroDTO findById(Integer id) {
        Optional<Intro> intro = introRepository.findById(id);
        ValidationUtil.isNull(intro,"Intro","id",id);
        return introMapper.toDto(intro.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IntroDTO create(Intro resources) {
        return introMapper.toDto(introRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Intro resources) {
        Optional<Intro> optionalIntro = introRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalIntro,"Intro","id",resources.getId());

        Intro intro = optionalIntro.get();
        // 此处需自己修改
        resources.setId(intro.getId());
        introRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        introRepository.deleteById(id.intValue());
    }
}