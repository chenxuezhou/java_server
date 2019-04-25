package me.zhengjie.modules.rate.repository;

import me.zhengjie.modules.rate.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface RateRepository extends JpaRepository<Rate, Integer>, JpaSpecificationExecutor {
}