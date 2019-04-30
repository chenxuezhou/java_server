package me.zhengjie.modules.dish.repository;

import me.zhengjie.modules.dish.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-04-30
*/
public interface DishRepository extends JpaRepository<Dish, Long>, JpaSpecificationExecutor {
}