package me.zhengjie.modules.user_opt.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Entity
@Data
@Table(name="users")
public class Users implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    /**
     * 用户名
     */
    @Column(name = "name")
    private String name;
}