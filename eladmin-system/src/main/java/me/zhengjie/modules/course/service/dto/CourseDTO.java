package me.zhengjie.modules.course.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Data
public class CourseDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;
}