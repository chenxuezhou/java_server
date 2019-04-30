package me.zhengjie.modules.address.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Entity
@Data
@Table(name="address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 地点名称
     */
    @Column(name = "name",nullable = false)
    private String name;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private Integer cusId;
}