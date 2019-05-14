package me.zhengjie.modules.teacher.repository;

import me.zhengjie.modules.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-14
*/
public interface TeacherRepository extends JpaRepository<Teacher, String>, JpaSpecificationExecutor {
}