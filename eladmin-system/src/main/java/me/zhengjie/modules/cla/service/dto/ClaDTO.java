package me.zhengjie.modules.cla.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-12
*/
@Data
public class ClaDTO implements Serializable {

    private Long id;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}