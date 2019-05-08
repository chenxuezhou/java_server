package me.zhengjie.modules.preview.repository;

import me.zhengjie.modules.preview.domain.Preview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-07
*/
public interface PreviewRepository extends JpaRepository<Preview, Integer>, JpaSpecificationExecutor {
}