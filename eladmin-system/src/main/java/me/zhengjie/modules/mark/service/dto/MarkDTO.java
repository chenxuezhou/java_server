package me.zhengjie.modules.mark.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Data
public class MarkDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 分数
     */
    private Float score;

    /**
     * 学生id
     */
    private String studentid;

    /**
     * 课程id
     */
    private String courseid;
}