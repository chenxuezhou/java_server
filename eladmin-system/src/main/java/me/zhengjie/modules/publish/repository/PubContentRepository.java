package me.zhengjie.modules.publish.repository;

import me.zhengjie.modules.publish.domain.PubContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-26
*/
public interface PubContentRepository extends JpaRepository<PubContent, Long>, JpaSpecificationExecutor {
}