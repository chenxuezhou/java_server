package me.zhengjie.modules.publish.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-26
*/
@Data
public class PubContentDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 资源id
     */
    private String resId;

    /**
     * 主题
     */
    private String topic;

    /**
     * 内容
     */
    private String content;
}