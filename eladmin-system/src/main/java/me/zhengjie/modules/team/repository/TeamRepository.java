package me.zhengjie.modules.team.repository;

import me.zhengjie.modules.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-14
*/
public interface TeamRepository extends JpaRepository<Team, String>, JpaSpecificationExecutor {
}