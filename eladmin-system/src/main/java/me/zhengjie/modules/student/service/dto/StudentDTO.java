package me.zhengjie.modules.student.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Data
public class StudentDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    private String code;

    /**
     * 名字
     */
    private String name;

    private String enrolldate;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 班级id
     */
    private String teamid;
}