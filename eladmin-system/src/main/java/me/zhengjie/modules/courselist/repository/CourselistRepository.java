package me.zhengjie.modules.courselist.repository;

import me.zhengjie.modules.courselist.domain.Courselist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface CourselistRepository extends JpaRepository<Courselist, Integer>, JpaSpecificationExecutor {
}