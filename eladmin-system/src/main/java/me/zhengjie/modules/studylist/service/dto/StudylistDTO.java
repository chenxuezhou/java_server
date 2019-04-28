package me.zhengjie.modules.studylist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Data
public class StudylistDTO implements Serializable {

    private Integer id;

    /**
     * 笔记标题
     */
    private String title;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 用户id
     */
    private Integer cusId;
}