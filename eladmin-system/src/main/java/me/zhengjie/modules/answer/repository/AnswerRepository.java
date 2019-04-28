package me.zhengjie.modules.answer.repository;

import me.zhengjie.modules.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-28
*/
public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor {
}