package me.zhengjie.modules.classdata.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="classdata")
public class Classdata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 详情展示图片
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 详情标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 价格
     */
    @Column(name = "money")
    private Integer money;

    /**
     * 参数id
     */
    @Column(name = "intro")
    private Integer intro;

    /**
     * 老师id
     */
    @Column(name = "teacher")
    private Integer teacher;

    /**
     * 评价id
     */
    @Column(name = "rate")
    private Integer rate;

    /**
     * 章节id
     */
    @Column(name = "chapterlist")
    private String chapterlist;
}