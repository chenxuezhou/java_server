package me.zhengjie.modules.typelist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-24
*/
@Data
public class TypelistDTO implements Serializable {

    private Integer id;

    /**
     * 图片
     */
    private String icon;

    /**
     * 类别内容
     */
    private String text;

    /**
     * 课程纲目id
     */
    private Integer ccId;
}