package me.zhengjie.modules.customers.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="customers")
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 昵称
     */
    @Column(name = "name",nullable = false)
    private String name;

    /**
     * 手机号
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 是否已购买
     */
    @Column(name = "ifBuy")
    private String ifbuy;

    /**
     * 购买课程
     */
    @Column(name = "courses")
    private String courses;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 头像
     */
    @Column(name = "photo")
    private String photo;

    /**
     * 班级
     */
    @Column(name = "classes")
    private String classes;
}