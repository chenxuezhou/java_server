package me.zhengjie.modules.technology.repository;

import me.zhengjie.modules.technology.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-03-12
*/
public interface TechnologyRepository extends JpaRepository<Technology, Long>, JpaSpecificationExecutor {
}