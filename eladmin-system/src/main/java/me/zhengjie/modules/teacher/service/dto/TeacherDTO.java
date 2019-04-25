package me.zhengjie.modules.teacher.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class TeacherDTO implements Serializable {

    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 名字
     */
    private String name;

    /**
     * 方向类别
     */
    private String type;

    /**
     * 详情id
     */
    private Integer claId;
}