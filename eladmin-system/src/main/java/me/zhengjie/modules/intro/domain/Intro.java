package me.zhengjie.modules.intro.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="intro")
public class Intro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 难度级别
     */
    @Column(name = "level")
    private String level;

    /**
     * 价格
     */
    @Column(name = "disccount")
    private Integer disccount;

    /**
     * 评分
     */
    @Column(name = "rate")
    private Double rate;

    /**
     * 人数
     */
    @Column(name = "people")
    private Integer people;

    /**
     * 详情id
     */
    @Column(name = "cla_id",nullable = false)
    private String claId;
}