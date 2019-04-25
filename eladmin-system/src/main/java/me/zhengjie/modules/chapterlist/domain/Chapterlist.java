package me.zhengjie.modules.chapterlist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="chapterlist")
public class Chapterlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 特定格式目录
     */
    @Column(name = "classlist")
    private String classlist;

    /**
     * 详细信息
     */
    @Column(name = "cla_id",nullable = false)
    private String claId;
}