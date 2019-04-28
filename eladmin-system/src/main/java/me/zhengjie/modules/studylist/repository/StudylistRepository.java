package me.zhengjie.modules.studylist.repository;

import me.zhengjie.modules.studylist.domain.Studylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-28
*/
public interface StudylistRepository extends JpaRepository<Studylist, Integer>, JpaSpecificationExecutor {
}