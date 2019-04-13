package me.zhengjie.modules.market.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-03-12
*/
@Data
public class MarketDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 创建日期
     */
    private Timestamp createTime;

    /**
     * 标题
     */
    private String title;

    /**
     * 主题内容
     */
    private String content;

    /**
     * 编号
     */
    private String number;
}