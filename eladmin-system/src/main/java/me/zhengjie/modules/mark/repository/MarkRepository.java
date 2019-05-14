package me.zhengjie.modules.mark.repository;

import me.zhengjie.modules.mark.domain.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-14
*/
public interface MarkRepository extends JpaRepository<Mark, String>, JpaSpecificationExecutor {
}