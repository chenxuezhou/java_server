package me.zhengjie.modules.answer.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Data
public class AnswerDTO implements Serializable {

    private Long id;

    /**
     * 用户答案
     */
    private String ansReal;

    /**
     * 用户id
     */
    private Integer cusId;

    /**
     * 习题id
     */
    private Integer exeId;

    /**
     * 成绩id
     */
    private Integer graId;
}