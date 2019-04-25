package me.zhengjie.modules.rate.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class RateDTO implements Serializable {

    private Integer id;

    /**
     * 评分
     */
    private String rate;

    /**
     * 评论id集合
     */
    private String commentlist;

    /**
     * 详细id
     */
    private Integer claId;
}