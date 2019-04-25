package me.zhengjie.modules.reclist.repository;

import me.zhengjie.modules.reclist.domain.Reclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface ReclistRepository extends JpaRepository<Reclist, Integer>, JpaSpecificationExecutor {
}