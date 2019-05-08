package me.zhengjie.modules.movies.repository;

import me.zhengjie.modules.movies.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-07
*/
public interface MoviesRepository extends JpaRepository<Movies, Integer>, JpaSpecificationExecutor {
}