package me.zhengjie.modules.collect.repository;

import me.zhengjie.modules.collect.domain.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-27
*/
public interface CollectRepository extends JpaRepository<Collect, Long>, JpaSpecificationExecutor {
}