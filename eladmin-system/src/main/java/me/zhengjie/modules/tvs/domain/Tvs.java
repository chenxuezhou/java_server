package me.zhengjie.modules.tvs.domain;

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
@Table(name="tvs")
public class Tvs implements Serializable {

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
     * 简介
     */
    @Column(name = "info")
    private String info;

    /**
     * 播放地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 添加时间
     */
    @Column(name = "addtime")
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