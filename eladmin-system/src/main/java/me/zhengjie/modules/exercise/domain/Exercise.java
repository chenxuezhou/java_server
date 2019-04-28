package me.zhengjie.modules.exercise.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Entity
@Data
@Table(name="exercise")
public class Exercise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 习题题目
     */
    @Column(name = "description",nullable = false)
    private String description;

    /**
     * 答案a
     */
    @Column(name = "ans_a",nullable = false)
    private String ansA;

    /**
     * 答案b
     */
    @Column(name = "ans_b",nullable = false)
    private String ansB;

    /**
     * 答案c
     */
    @Column(name = "ans_c",nullable = false)
    private String ansC;

    /**
     * 答案d
     */
    @Column(name = "ans_d",nullable = false)
    private String ansD;

    /**
     * 正确答案
     */
    @Column(name = "ans",nullable = false)
    private String ans;

    /**
     * 所属章节
     */
    @Column(name = "cha_id",nullable = false)
    private String chaId;
}