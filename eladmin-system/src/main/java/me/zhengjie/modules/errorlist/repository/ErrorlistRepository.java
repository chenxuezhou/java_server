package me.zhengjie.modules.errorlist.repository;

import me.zhengjie.modules.errorlist.domain.Errorlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-28
*/
public interface ErrorlistRepository extends JpaRepository<Errorlist, Integer>, JpaSpecificationExecutor {
}