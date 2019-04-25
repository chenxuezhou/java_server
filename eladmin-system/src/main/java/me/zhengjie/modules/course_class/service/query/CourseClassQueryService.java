package me.zhengjie.modules.course_class.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.course_class.domain.CourseClass;
import me.zhengjie.modules.course_class.service.dto.CourseClassDTO;
import me.zhengjie.modules.course_class.repository.CourseClassRepository;
import me.zhengjie.modules.course_class.service.mapper.CourseClassMapper;
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
@CacheConfig(cacheNames = "courseClass")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseClassQueryService {

    @Autowired
    private CourseClassRepository courseClassRepository;

    @Autowired
    private CourseClassMapper courseClassMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CourseClassDTO courseClass, Pageable pageable){
        Page<CourseClass> page = courseClassRepository.findAll(new Spec(courseClass),pageable);
        return PageUtil.toPage(page.map(courseClassMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(CourseClassDTO courseClass){
        return courseClassMapper.toDto(courseClassRepository.findAll(new Spec(courseClass)));
    }

    class Spec implements Specification<CourseClass> {

        private CourseClassDTO courseClass;

        public Spec(CourseClassDTO courseClass){
            this.courseClass = courseClass;
        }

        @Override
        public Predicate toPredicate(Root<CourseClass> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}