package me.zhengjie.modules.movies.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Data
public class MoviesDTO implements Serializable {

    private Integer id;

    /**
     * 剧名
     */
    private String title;

    /**
     * 播放地址
     */
    private String url;

    /**
     * 简介
     */
    private String info;

    /**
     * 展示logo
     */
    private String logo;

    /**
     * 评分
     */
    private String score;

    /**
     * 播放次数
     */
    private Integer playnum;

    /**
     * 评论数
     */
    private Integer commentnum;

    private Timestamp releaseTime;

    /**
     * 添加时间
     */
    private Timestamp addtime;

    /**
     * 类型
     */
    private String type;

    /**
     * 地区
     */
    private String area;

    /**
     * 主题
     */
    private String topic;
}