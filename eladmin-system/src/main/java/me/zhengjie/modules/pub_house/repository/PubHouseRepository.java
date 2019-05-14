package me.zhengjie.modules.pub_house.repository;

import me.zhengjie.modules.pub_house.domain.PubHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-14
*/
public interface PubHouseRepository extends JpaRepository<PubHouse, Long>, JpaSpecificationExecutor {
}