package me.zhengjie.modules.grade.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Entity
@Data
@Table(name="grade")
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户回答情况
     */
    @Column(name = "grade",nullable = false)
    private String grade;

    /**
     * 用户id
     */
    @Column(name = "cus_id",nullable = false)
    private Integer cusId;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Timestamp createTime;
}