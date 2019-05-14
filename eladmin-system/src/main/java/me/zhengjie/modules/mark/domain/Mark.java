package me.zhengjie.modules.mark.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Entity
@Data
@Table(name="mark")
public class Mark implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    /**
     * 分数
     */
    @Column(name = "score")
    private Float score;

    /**
     * 学生id
     */
    @Column(name = "studentid")
    private String studentid;

    /**
     * 课程id
     */
    @Column(name = "courseid")
    private String courseid;
}