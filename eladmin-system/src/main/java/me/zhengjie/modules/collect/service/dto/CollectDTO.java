package me.zhengjie.modules.collect.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-27
*/
@Data
public class CollectDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

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
    private String pubId;
}