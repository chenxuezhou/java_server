package me.zhengjie.modules.comments.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Data
public class CommentsDTO implements Serializable {

    private Integer id;

    /**
     * 评论
     */
    private String content;

    /**
     * 电影id
     */
    private Integer movieId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 添加时间
     */
    private Timestamp addtime;
}