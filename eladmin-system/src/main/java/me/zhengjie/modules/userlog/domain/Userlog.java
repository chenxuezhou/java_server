package me.zhengjie.modules.userlog.domain;

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
@Table(name="userlog")
public class Userlog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 登陆时间
     */
    @Column(name = "login_time",nullable = false)
    private Timestamp loginTime;

    /**
     * ip
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;
}