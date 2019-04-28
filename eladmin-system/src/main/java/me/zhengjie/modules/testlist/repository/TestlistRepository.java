package me.zhengjie.modules.testlist.repository;

import me.zhengjie.modules.testlist.domain.Testlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-28
*/
public interface TestlistRepository extends JpaRepository<Testlist, Integer>, JpaSpecificationExecutor {
}