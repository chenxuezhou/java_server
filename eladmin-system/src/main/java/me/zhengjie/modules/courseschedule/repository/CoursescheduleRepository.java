package me.zhengjie.modules.courseschedule.repository;

import me.zhengjie.modules.courseschedule.domain.Courseschedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-14
*/
public interface CoursescheduleRepository extends JpaRepository<Courseschedule, String>, JpaSpecificationExecutor {
}