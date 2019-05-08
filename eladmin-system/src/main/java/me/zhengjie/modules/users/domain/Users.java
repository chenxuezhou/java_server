package me.zhengjie.modules.users.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Entity
@Data
@Table(name="users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "uname")
    private String uname;

    @Column(name = "pwd")
    private String pwd;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 简介
     */
    @Column(name = "info")
    private String info;

    /**
     * 头像
     */
    @Column(name = "face")
    private String face;

    @Column(name = "addtime",nullable = false)
    private Timestamp addtime;
}