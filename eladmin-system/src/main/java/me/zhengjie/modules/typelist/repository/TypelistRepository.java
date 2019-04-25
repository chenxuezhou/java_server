package me.zhengjie.modules.typelist.repository;

import me.zhengjie.modules.typelist.domain.Typelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface TypelistRepository extends JpaRepository<Typelist, Integer>, JpaSpecificationExecutor {
}