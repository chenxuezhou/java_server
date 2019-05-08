package me.zhengjie.modules.colmovie.repository;

import me.zhengjie.modules.colmovie.domain.Colmovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-07
*/
public interface ColmovieRepository extends JpaRepository<Colmovie, Integer>, JpaSpecificationExecutor {
}