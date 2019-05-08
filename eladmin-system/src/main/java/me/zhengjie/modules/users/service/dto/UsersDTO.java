package me.zhengjie.modules.users.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Data
public class UsersDTO implements Serializable {

    private Integer id;

    /**
     * 用户名
     */
    private String uname;

    private String pwd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 简介
     */
    private String info;

    /**
     * 头像
     */
    private String face;

    private Timestamp addtime;
}