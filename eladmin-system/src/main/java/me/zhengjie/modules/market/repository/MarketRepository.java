package me.zhengjie.modules.market.repository;

import me.zhengjie.modules.market.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-03-12
*/
public interface MarketRepository extends JpaRepository<Market, Long>, JpaSpecificationExecutor {
}