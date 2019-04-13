package me.zhengjie.modules.peccancy.repository;

import me.zhengjie.modules.peccancy.domain.Peccancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-03-12
*/
public interface PeccancyRepository extends JpaRepository<Peccancy, Long>, JpaSpecificationExecutor {
}