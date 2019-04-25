package me.zhengjie.modules.customers.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class CustomersDTO implements Serializable {

    private Integer id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 是否已购买
     */
    private String ifbuy;

    /**
     * 购买课程
     */
    private String courses;

    /**
     * 性别
     */
    private String sex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String photo;

    /**
     * 班级
     */
    private String classes;
}