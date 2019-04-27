package me.zhengjie.modules.sub.repository;

import me.zhengjie.modules.sub.domain.Sub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-27
*/
public interface SubRepository extends JpaRepository<Sub, Long>, JpaSpecificationExecutor {
}