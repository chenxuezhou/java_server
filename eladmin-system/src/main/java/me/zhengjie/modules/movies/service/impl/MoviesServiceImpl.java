package me.zhengjie.modules.movies.service.impl;

import me.zhengjie.modules.movies.domain.Movies;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.movies.repository.MoviesRepository;
import me.zhengjie.modules.movies.service.MoviesService;
import me.zhengjie.modules.movies.service.dto.MoviesDTO;
import me.zhengjie.modules.movies.service.mapper.MoviesMapper;
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
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private MoviesMapper moviesMapper;

    @Override
    public MoviesDTO findById(Integer id) {
        Optional<Movies> movies = moviesRepository.findById(id);
        ValidationUtil.isNull(movies,"Movies","id",id);
        return moviesMapper.toDto(movies.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MoviesDTO create(Movies resources) {
        return moviesMapper.toDto(moviesRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Movies resources) {
        Optional<Movies> optionalMovies = moviesRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMovies,"Movies","id",resources.getId());

        Movies movies = optionalMovies.get();
        // 此处需自己修改
        resources.setId(movies.getId());
        moviesRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        moviesRepository.deleteById(id.intValue());
    }
}