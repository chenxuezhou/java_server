package me.zhengjie.modules.course_class.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class CourseClassDTO implements Serializable {

    private Integer id;

    /**
     * 类别名称
     */
    private String title;
}