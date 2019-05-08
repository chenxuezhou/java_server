package me.zhengjie.modules.tvs.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Data
public class TvsDTO implements Serializable {

    private Integer id;

    /**
     * 剧名
     */
    private String title;

    /**
     * 简介
     */
    private String info;

    /**
     * 播放地址
     */
    private String url;

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