package me.zhengjie.modules.v_order.repository;

import me.zhengjie.modules.v_order.domain.VOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-29
*/
public interface VOrderRepository extends JpaRepository<VOrder, Long>, JpaSpecificationExecutor {
}