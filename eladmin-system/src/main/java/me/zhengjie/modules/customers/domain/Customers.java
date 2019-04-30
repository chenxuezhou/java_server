package me.zhengjie.modules.customers.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Entity
@Data
@Table(name="customers")
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 默认地址
     */
    @Column(name = "address_id")
    private String addressId;
}