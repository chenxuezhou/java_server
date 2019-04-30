package me.zhengjie.modules.dish.service.impl;

import me.zhengjie.modules.dish.domain.Dish;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.modules.dish.repository.DishRepository;
import me.zhengjie.modules.dish.service.DishService;
import me.zhengjie.modules.dish.service.dto.DishDTO;
import me.zhengjie.modules.dish.service.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
* @author jie
* @date 2019-04-30
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishMapper dishMapper;

    @Override
    public DishDTO findById(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        ValidationUtil.isNull(dish,"Dish","id",id);
        return dishMapper.toDto(dish.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DishDTO create(Dish resources) {
        return dishMapper.toDto(dishRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dish resources) {
        Optional<Dish> optionalDish = dishRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDish,"Dish","id",resources.getId());

        Dish dish = optionalDish.get();
        // 此处需自己修改
        resources.setId(dish.getId());
        dishRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}