package me.zhengjie.modules.v_order.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Entity
@Data
@Table(name="v_order")
public class VOrder implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private String cusId;

    /**
     * 订单状态1:预约，2：已确认服务
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 私厨id
     */
    @Column(name = "cook_id")
    private String cookId;

    /**
     * 预约开始时间
     */
    @Column(name = "start_time",nullable = false)
    private Timestamp startTime;

    /**
     * 预约结束时间
     */
    @Column(name = "end_time",nullable = false)
    private Timestamp endTime;
}