package me.zhengjie.modules.v_order.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Data
public class VOrderDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private String cusId;

    /**
     * 订单状态1:预约，2：已确认服务
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 私厨id
     */
    private String cookId;

    /**
     * 预约开始时间
     */
    private Timestamp startTime;

    /**
     * 预约结束时间
     */
    private Timestamp endTime;
}