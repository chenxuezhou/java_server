package me.zhengjie.modules.grade.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Data
public class GradeDTO implements Serializable {

    private Long id;

    /**
     * 用户回答情况
     */
    private String grade;

    /**
     * 用户id
     */
    private Integer cusId;

    /**
     * 创建日期
     */
    private Timestamp createTime;
}