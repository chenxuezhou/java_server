package me.zhengjie.modules.meet.repository;

import me.zhengjie.modules.meet.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-13
*/
public interface MeetingRepository extends JpaRepository<Meeting, Long>, JpaSpecificationExecutor {
}