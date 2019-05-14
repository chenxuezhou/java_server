package me.zhengjie.modules.course.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Entity
@Data
@Table(name="course")
public class Course implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
}