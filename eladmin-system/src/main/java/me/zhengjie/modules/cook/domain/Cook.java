package me.zhengjie.modules.cook.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-30
*/
@Entity
@Data
@Table(name="cook")
public class Cook implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 介绍
     */
    @Column(name = "description")
    private String description;

    /**
     * 岁数
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 菜品id
     */
    @Column(name = "dish_id")
    private String dishId;

    /**
     * 住址
     */
    @Column(name = "adress")
    private String adress;
}