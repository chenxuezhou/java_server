package me.zhengjie.modules.reclist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="reclist")
public class Reclist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 课程名
     */
    @Column(name = "title")
    private String title;

    /**
     * 难度
     */
    @Column(name = "level")
    private String level;

    /**
     * 图片展示
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 价格
     */
    @Column(name = "money")
    private Integer money;

    /**
     * 报名人数
     */
    @Column(name = "people")
    private Integer people;

    /**
     * 是否停止
     */
    @Column(name = "istop")
    private String istop;

    /**
     * 关联的详情id
     */
    @Column(name = "cla_id",nullable = false)
    private Integer claId;

    /**
     * 参数id
     */
    @Column(name = "intro")
    private String intro;
}