package me.zhengjie.modules.commentlist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class CommentlistDTO implements Serializable {

    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 名称
     */
    private String name;

    /**
     * 评分
     */
    private Double rate;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评分id
     */
    private Integer rateId;

    /**
     * 用户id
     */
    private Integer cusId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 详情id
     */
    private Integer cdId;
}