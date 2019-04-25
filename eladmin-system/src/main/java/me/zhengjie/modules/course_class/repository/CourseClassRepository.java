package me.zhengjie.modules.course_class.repository;

import me.zhengjie.modules.course_class.domain.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface CourseClassRepository extends JpaRepository<CourseClass, Integer>, JpaSpecificationExecutor {
}