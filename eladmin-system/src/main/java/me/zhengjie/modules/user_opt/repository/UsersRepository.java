package me.zhengjie.modules.user_opt.repository;

import me.zhengjie.modules.user_opt.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-14
*/
public interface UsersRepository extends JpaRepository<Users, String>, JpaSpecificationExecutor {
}