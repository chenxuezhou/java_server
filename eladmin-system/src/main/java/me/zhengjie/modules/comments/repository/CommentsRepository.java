package me.zhengjie.modules.comments.repository;

import me.zhengjie.modules.comments.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-05-07
*/
public interface CommentsRepository extends JpaRepository<Comments, Integer>, JpaSpecificationExecutor {
}