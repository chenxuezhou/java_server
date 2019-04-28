package me.zhengjie.modules.exercise.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.exercise.domain.Exercise;
import me.zhengjie.modules.exercise.service.dto.ExerciseDTO;
import me.zhengjie.modules.exercise.repository.ExerciseRepository;
import me.zhengjie.modules.exercise.service.mapper.ExerciseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "exercise")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ExerciseQueryService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseMapper exerciseMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExerciseDTO exercise, Pageable pageable){
        Page<Exercise> page = exerciseRepository.findAll(new Spec(exercise),pageable);
        return PageUtil.toPage(page.map(exerciseMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ExerciseDTO exercise){
        return exerciseMapper.toDto(exerciseRepository.findAll(new Spec(exercise)));
    }

    class Spec implements Specification<Exercise> {

        private ExerciseDTO exercise;

        public Spec(ExerciseDTO exercise){
            this.exercise = exercise;
        }

        @Override
        public Predicate toPredicate(Root<Exercise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(exercise.getDescription())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("description").as(String.class),"%"+exercise.getDescription()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}