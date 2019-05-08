package me.zhengjie.modules.userlog.repository;

import me.zhengjie.modules.userlog.domain.Userlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-07
*/
public interface UserlogRepository extends JpaRepository<Userlog, Integer>, JpaSpecificationExecutor {
}