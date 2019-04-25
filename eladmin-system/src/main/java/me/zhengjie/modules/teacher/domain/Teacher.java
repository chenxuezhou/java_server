package me.zhengjie.modules.teacher.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="teacher")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 方向类别
     */
    @Column(name = "type")
    private String type;

    /**
     * 详情id
     */
    @Column(name = "cla_id",nullable = false)
    private Integer claId;
}