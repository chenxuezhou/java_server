package me.zhengjie.modules.rate.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Entity
@Data
@Table(name="rate")
public class Rate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 评分
     */
    @Column(name = "rate")
    private String rate;

    /**
     * 评论id集合
     */
    @Column(name = "commentlist")
    private String commentlist;

    /**
     * 详细id
     */
    @Column(name = "cla_id",nullable = false)
    private Integer claId;
}