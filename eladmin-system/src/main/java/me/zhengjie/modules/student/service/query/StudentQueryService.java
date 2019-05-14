package me.zhengjie.modules.student.service.query;

import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.modules.student.service.dto.StudentDTO;
import me.zhengjie.modules.student.repository.StudentRepository;
import me.zhengjie.modules.student.service.mapper.StudentMapper;
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
@CacheConfig(cacheNames = "student")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StudentQueryService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(StudentDTO student, Pageable pageable){
        Page<Student> page = studentRepository.findAll(new Spec(student),pageable);
        return PageUtil.toPage(page.map(studentMapper::toDto));
    }

    /**
    * 不分页
    */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(StudentDTO student){
        return studentMapper.toDto(studentRepository.findAll(new Spec(student)));
    }

    class Spec implements Specification<Student> {

        private StudentDTO student;

        public Spec(StudentDTO student){
            this.student = student;
        }

        @Override
        public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
        }
    }
}