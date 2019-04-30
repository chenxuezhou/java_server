package me.zhengjie.modules.dish.domain;

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
@Table(name="dish")
public class Dish implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 类别
     */
    @Column(name = "type")
    private String type;

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
     * 辣度
     */
    @Column(name = "level")
    private String level;

    /**
     * 菜品名
     */
    @Column(name = "name")
    private String name;
}