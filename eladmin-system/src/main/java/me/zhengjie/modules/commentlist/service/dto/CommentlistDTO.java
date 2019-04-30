package me.zhengjie.modules.commentlist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Data
public class CommentlistDTO implements Serializable {

    private Long id;

    /**
     * 评分
     */
    private Integer rate;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 用户id
     */
    private Integer cusId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 私厨id
     */
    private Integer cookId;
}