package me.zhengjie.modules.notify.repository;

import me.zhengjie.modules.notify.domain.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-03-12
*/
public interface NotifyRepository extends JpaRepository<Notify, Long>, JpaSpecificationExecutor {
}