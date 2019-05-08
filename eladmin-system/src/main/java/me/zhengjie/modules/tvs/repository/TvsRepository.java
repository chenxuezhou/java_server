package me.zhengjie.modules.tvs.repository;

import me.zhengjie.modules.tvs.domain.Tvs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-07
*/
public interface TvsRepository extends JpaRepository<Tvs, Integer>, JpaSpecificationExecutor {
}