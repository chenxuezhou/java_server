package me.zhengjie.modules.chapterlist.repository;

import me.zhengjie.modules.chapterlist.domain.Chapterlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface ChapterlistRepository extends JpaRepository<Chapterlist, Integer>, JpaSpecificationExecutor {
}