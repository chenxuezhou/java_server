package me.zhengjie.modules.intro.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class IntroDTO implements Serializable {

    private Integer id;

    /**
     * 难度级别
     */
    private String level;

    /**
     * 价格
     */
    private Integer disccount;

    /**
     * 评分
     */
    private Double rate;

    /**
     * 人数
     */
    private Integer people;

    /**
     * 详情id
     */
    private String claId;
}