package me.zhengjie.modules.teacher.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.modules.teacher.service.dto.TeacherDTO;
import me.zhengjie.modules.teacher.repository.TeacherRepository;
import me.zhengjie.modules.teacher.service.mapper.TeacherMapper;
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
@CacheConfig(cacheNames = "teacher")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TeacherQueryService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TeacherDTO teacher, Pageable pageable){
        Page<Teacher> page = teacherRepository.findAll(new Spec(teacher),pageable);
        return PageUtil.toPage(page.map(teacherMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(TeacherDTO teacher){
        return teacherMapper.toDto(teacherRepository.findAll(new Spec(teacher)));
    }

    class Spec implements Specification<Teacher> {

        private TeacherDTO teacher;

        public Spec(TeacherDTO teacher){
            this.teacher = teacher;
        }

        @Override
        public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                if(!ObjectUtils.isEmpty(teacher.getName())){
                    /**
                    * 模糊
                    */
                    list.add(cb.like(root.get("name").as(String.class),"%"+teacher.getName()+"%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}