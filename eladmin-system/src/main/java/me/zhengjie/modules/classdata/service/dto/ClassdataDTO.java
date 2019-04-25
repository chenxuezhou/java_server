package me.zhengjie.modules.classdata.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class ClassdataDTO implements Serializable {

    private Integer id;

    /**
     * 详情展示图片
     */
    private String icon;

    /**
     * 详情标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 价格
     */
    private Integer money;

    /**
     * 参数id
     */
    private Integer intro;

    /**
     * 老师id
     */
    private Integer teacher;

    /**
     * 评价id
     */
    private Integer rate;

    /**
     * 章节id
     */
    private String chapterlist;
}