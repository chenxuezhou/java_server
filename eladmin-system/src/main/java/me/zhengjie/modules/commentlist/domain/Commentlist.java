package me.zhengjie.modules.commentlist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Entity
@Data
@Table(name="commentlist")
public class Commentlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 评分
     */
    @Column(name = "rate",nullable = false)
    private Integer rate;

    /**
     * 评论内容
     */
    @Column(name = "comment")
    private String comment;

    /**
     * 用户id
     */
    @Column(name = "cus_id",nullable = false)
    private Integer cusId;

    /**
     * 订单id
     */
    @Column(name = "order_id",nullable = false)
    private Integer orderId;

    /**
     * 私厨id
     */
    @Column(name = "cook_id",nullable = false)
    private Integer cookId;
}