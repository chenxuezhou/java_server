package me.zhengjie.modules.chapterlist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class ChapterlistDTO implements Serializable {

    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 特定格式目录
     */
    private String classlist;

    /**
     * 详细信息
     */
    private String claId;
}