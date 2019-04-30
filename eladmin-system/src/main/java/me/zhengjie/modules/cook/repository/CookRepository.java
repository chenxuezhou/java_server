package me.zhengjie.modules.cook.repository;

import me.zhengjie.modules.cook.domain.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-30
*/
public interface CookRepository extends JpaRepository<Cook, Long>, JpaSpecificationExecutor {
}