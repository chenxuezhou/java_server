package me.zhengjie.modules.cook.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-30
*/
@Data
public class CookDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 介绍
     */
    private String description;

    /**
     * 岁数
     */
    private Integer age;

    /**
     * 菜品id
     */
    private String dishId;

    /**
     * 住址
     */
    private String adress;
}