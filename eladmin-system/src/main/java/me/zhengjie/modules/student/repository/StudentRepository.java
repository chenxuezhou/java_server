package me.zhengjie.modules.student.repository;

import me.zhengjie.modules.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-14
*/
public interface StudentRepository extends JpaRepository<Student, String>, JpaSpecificationExecutor {
}