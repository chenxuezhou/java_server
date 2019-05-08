package me.zhengjie.modules.colmovie.domain;

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
@Table(name="colmovie")
public class Colmovie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 收藏地址
     */
    @Column(name = "movie_url")
    private String movieUrl;

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