package me.zhengjie.modules.courseschedule.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Data
public class CoursescheduleDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    private String semester;

    /**
     * 学分
     */
    private Float score;

    /**
     * 班级id
     */
    private String teamid;

    /**
     * 课程id
     */
    private String courseid;

    /**
     * 老师id
     */
    private String teacherid;
}