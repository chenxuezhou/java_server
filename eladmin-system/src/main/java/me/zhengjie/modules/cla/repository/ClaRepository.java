package me.zhengjie.modules.cla.repository;

import me.zhengjie.modules.cla.domain.Cla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface ClaRepository extends JpaRepository<Cla, Long>, JpaSpecificationExecutor {
}