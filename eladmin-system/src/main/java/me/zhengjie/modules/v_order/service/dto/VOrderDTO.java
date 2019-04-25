package me.zhengjie.modules.v_order.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-25
*/
@Data
public class VOrderDTO implements Serializable {

    private Integer id;

    /**
     * 订单总价
     */
    private Double num;

    /**
     * 详情集合id
     */
    private String cdListId;

    /**
     * 用户id
     */
    private Integer cusId;
}