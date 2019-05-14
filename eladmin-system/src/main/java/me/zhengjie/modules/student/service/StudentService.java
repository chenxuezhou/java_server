package me.zhengjie.modules.student.service;

import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.modules.student.service.dto.StudentDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
* @author jie
* @date 2019-05-14
*/
@CacheConfig(cacheNames = "student")
public interface StudentService {

    /**
     * findById
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    StudentDTO findById(String id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    StudentDTO create(Student resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(Student resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(String id);
}