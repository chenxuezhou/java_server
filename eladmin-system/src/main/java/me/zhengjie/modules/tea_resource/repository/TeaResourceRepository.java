package me.zhengjie.modules.tea_resource.repository;

import me.zhengjie.modules.tea_resource.domain.TeaResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-12
*/
public interface TeaResourceRepository extends JpaRepository<TeaResource, Long>, JpaSpecificationExecutor {
}