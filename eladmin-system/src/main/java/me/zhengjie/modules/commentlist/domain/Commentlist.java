package me.zhengjie.modules.commentlist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="commentlist")
public class Commentlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 评分
     */
    @Column(name = "rate")
    private Double rate;

    /**
     * 评论内容
     */
    @Column(name = "comment")
    private String comment;

    /**
     * 评分id
     */
    @Column(name = "rate_id")
    private Integer rateId;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private Integer cusId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 详情id
     */
    @Column(name = "cd_id")
    private Integer cdId;
}