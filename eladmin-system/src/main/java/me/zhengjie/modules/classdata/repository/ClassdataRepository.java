package me.zhengjie.modules.classdata.repository;

import me.zhengjie.modules.classdata.domain.Classdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface ClassdataRepository extends JpaRepository<Classdata, Integer>, JpaSpecificationExecutor {
}