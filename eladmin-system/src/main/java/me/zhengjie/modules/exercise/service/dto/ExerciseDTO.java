package me.zhengjie.modules.exercise.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Data
public class ExerciseDTO implements Serializable {

    private Long id;

    /**
     * 习题题目
     */
    private String description;

    /**
     * 答案a
     */
    private String ansA;

    /**
     * 答案b
     */
    private String ansB;

    /**
     * 答案c
     */
    private String ansC;

    /**
     * 答案d
     */
    private String ansD;

    /**
     * 正确答案
     */
    private String ans;

    /**
     * 所属章节
     */
    private String chaId;
}