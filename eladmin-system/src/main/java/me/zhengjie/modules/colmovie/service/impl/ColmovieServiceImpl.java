package me.zhengjie.modules.colmovie.service.impl;

import me.zhengjie.modules.colmovie.domain.Colmovie;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.colmovie.repository.ColmovieRepository;
import me.zhengjie.modules.colmovie.service.ColmovieService;
import me.zhengjie.modules.colmovie.service.dto.ColmovieDTO;
import me.zhengjie.modules.colmovie.service.mapper.ColmovieMapper;
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
public class ColmovieServiceImpl implements ColmovieService {

    @Autowired
    private ColmovieRepository colmovieRepository;

    @Autowired
    private ColmovieMapper colmovieMapper;

    @Override
    public ColmovieDTO findById(Integer id) {
        Optional<Colmovie> colmovie = colmovieRepository.findById(id);
        ValidationUtil.isNull(colmovie,"Colmovie","id",id);
        return colmovieMapper.toDto(colmovie.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ColmovieDTO create(Colmovie resources) {
        return colmovieMapper.toDto(colmovieRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Colmovie resources) {
        Optional<Colmovie> optionalColmovie = colmovieRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalColmovie,"Colmovie","id",resources.getId());

        Colmovie colmovie = optionalColmovie.get();
        // 此处需自己修改
        resources.setId(colmovie.getId());
        colmovieRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        colmovieRepository.deleteById(id.intValue());
    }
}