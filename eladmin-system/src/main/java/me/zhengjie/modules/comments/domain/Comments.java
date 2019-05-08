package me.zhengjie.modules.comments.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Entity
@Data
@Table(name="comments")
public class Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 评论
     */
    @Column(name = "content")
    private String content;

    /**
     * 电影id
     */
    @Column(name = "movie_id")
    private Integer movieId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 添加时间
     */
    @Column(name = "addtime",nullable = false)
    private Timestamp addtime;
}