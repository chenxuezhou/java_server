package me.zhengjie.modules.courseschedule.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Entity
@Data
@Table(name="courseschedule")
public class Courseschedule implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "semester")
    private String semester;

    /**
     * 学分
     */
    @Column(name = "score")
    private Float score;

    /**
     * 班级id
     */
    @Column(name = "teamid")
    private String teamid;

    /**
     * 课程id
     */
    @Column(name = "courseid")
    private String courseid;

    /**
     * 老师id
     */
    @Column(name = "teacherid")
    private String teacherid;
}