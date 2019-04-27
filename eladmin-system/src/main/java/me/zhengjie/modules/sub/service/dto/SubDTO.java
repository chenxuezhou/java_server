package me.zhengjie.modules.sub.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-27
*/
@Data
public class SubDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 订阅
     */
    private Integer dinyue;

    /**
     * 主题
     */
    private String topic;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收藏
     */
    private Integer soucan;
}