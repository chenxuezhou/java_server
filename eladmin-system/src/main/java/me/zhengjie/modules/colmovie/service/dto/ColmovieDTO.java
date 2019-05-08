package me.zhengjie.modules.colmovie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Data
public class ColmovieDTO implements Serializable {

    private Integer id;

    /**
     * 收藏地址
     */
    private String movieUrl;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 添加时间
     */
    private Timestamp addtime;
}