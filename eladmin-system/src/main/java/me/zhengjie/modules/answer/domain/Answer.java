package me.zhengjie.modules.answer.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Entity
@Data
@Table(name="answer")
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户答案
     */
    @Column(name = "ans_real",nullable = false)
    private String ansReal;

    /**
     * 用户id
     */
    @Column(name = "cus_id",nullable = false)
    private Integer cusId;

    /**
     * 习题id
     */
    @Column(name = "exe_id",nullable = false)
    private Integer exeId;

    /**
     * 成绩id
     */
    @Column(name = "gra_id",nullable = false)
    private Integer graId;
}