package me.zhengjie.modules.users.repository;

import me.zhengjie.modules.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-07
*/
public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor {
}