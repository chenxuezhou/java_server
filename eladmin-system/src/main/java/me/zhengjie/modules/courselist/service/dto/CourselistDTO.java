package me.zhengjie.modules.courselist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class CourselistDTO implements Serializable {

    private Integer id;

    /**
     * 图片地址
     */
    private String icon;

    /**
     * 标题
     */
    private String title;

    /**
     * 难度
     */
    private String level;

    /**
     * 类型
     */
    private String type;

    /**
     * 人数
     */
    private Integer people;

    /**
     * 课程纲目id
     */
    private Integer ccId;
}