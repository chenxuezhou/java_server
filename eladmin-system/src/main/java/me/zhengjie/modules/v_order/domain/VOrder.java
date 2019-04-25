package me.zhengjie.modules.v_order.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-25
*/
@Entity
@Data
@Table(name="v_order")
public class VOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 订单总价
     */
    @Column(name = "num")
    private Double num;

    /**
     * 详情集合id
     */
    @Column(name = "cd_list_id")
    private String cdListId;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private Integer cusId;
}