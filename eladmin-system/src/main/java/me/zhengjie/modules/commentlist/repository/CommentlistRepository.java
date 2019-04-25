package me.zhengjie.modules.commentlist.repository;

import me.zhengjie.modules.commentlist.domain.Commentlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface CommentlistRepository extends JpaRepository<Commentlist, Integer>, JpaSpecificationExecutor {
}