package me.zhengjie.modules.courselist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="courselist")
public class Courselist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 图片地址
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 难度
     */
    @Column(name = "level")
    private String level;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 人数
     */
    @Column(name = "people")
    private Integer people;

    /**
     * 课程纲目id
     */
    @Column(name = "cc_id",nullable = false)
    private Integer ccId;
}