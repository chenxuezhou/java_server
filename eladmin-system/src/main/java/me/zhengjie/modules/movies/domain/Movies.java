package me.zhengjie.modules.movies.domain;

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
@Table(name="movies")
public class Movies implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 剧名
     */
    @Column(name = "title")
    private String title;

    /**
     * 播放地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 简介
     */
    @Column(name = "info")
    private String info;

    /**
     * 展示logo
     */
    @Column(name = "logo")
    private String logo;

    /**
     * 评分
     */
    @Column(name = "score")
    private String score;

    /**
     * 播放次数
     */
    @Column(name = "playnum")
    private Integer playnum;

    /**
     * 评论数
     */
    @Column(name = "commentnum")
    private Integer commentnum;

    @Column(name = "release_time",nullable = false)
    private Timestamp releaseTime;

    /**
     * 添加时间
     */
    @Column(name = "addtime",nullable = false)
    private Timestamp addtime;

    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 地区
     */
    @Column(name = "area")
    private String area;

    /**
     * 主题
     */
    @Column(name = "topic")
    private String topic;
}