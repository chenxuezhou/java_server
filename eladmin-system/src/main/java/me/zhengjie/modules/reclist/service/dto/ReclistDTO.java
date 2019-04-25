package me.zhengjie.modules.reclist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class ReclistDTO implements Serializable {

    private Integer id;

    /**
     * 课程名
     */
    private String title;

    /**
     * 难度
     */
    private String level;

    /**
     * 图片展示
     */
    private String icon;

    /**
     * 价格
     */
    private Integer money;

    /**
     * 报名人数
     */
    private Integer people;

    /**
     * 是否停止
     */
    private String istop;

    /**
     * 关联的详情id
     */
    private Integer claId;

    /**
     * 参数id
     */
    private String intro;
}