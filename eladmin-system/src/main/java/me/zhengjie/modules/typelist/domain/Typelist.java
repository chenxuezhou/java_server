package me.zhengjie.modules.typelist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="typelist")
public class Typelist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 图片
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 类别内容
     */
    @Column(name = "text")
    private String text;

    /**
     * 课程纲目id
     */
    @Column(name = "cc_id",nullable = false)
    private Integer ccId;
}