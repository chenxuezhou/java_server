package me.zhengjie.modules.intro.repository;

import me.zhengjie.modules.intro.domain.Intro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-24
*/
public interface IntroRepository extends JpaRepository<Intro, Integer>, JpaSpecificationExecutor {
}