package me.zhengjie.modules.student.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Entity
@Data
@Table(name="student")
public class Student implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "code")
    private String code;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    @Column(name = "enrolldate")
    private String enrolldate;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 班级id
     */
    @Column(name = "teamid")
    private String teamid;
}