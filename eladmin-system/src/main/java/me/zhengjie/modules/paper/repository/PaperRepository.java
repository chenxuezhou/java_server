package me.zhengjie.modules.paper.repository;

import me.zhengjie.modules.paper.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-03-12
*/
public interface PaperRepository extends JpaRepository<Paper, Long>, JpaSpecificationExecutor {
}