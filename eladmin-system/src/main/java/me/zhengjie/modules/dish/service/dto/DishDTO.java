package me.zhengjie.modules.dish.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-30
*/
@Data
public class DishDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 类别
     */
    private String type;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 介绍
     */
    private String description;

    /**
     * 辣度
     */
    private String level;

    /**
     * 菜品名
     */
    private String name;
}