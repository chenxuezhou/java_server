package me.zhengjie.modules.pub_house.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-04
*/
@Entity
@Data
@Table(name="pub_house")
public class PubHouse implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 卫浴数目
     */
    @Column(name = "toiletNum")
    private String toiletnum;

    /**
     * 客厅数目
     */
    @Column(name = "parlourNum")
    private String parlournum;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 资源id
     */
    @Column(name = "cover")
    private String cover;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 城市
     */
    @Column(name = "area")
    private String area;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private String cusId;

    /**
     * 居室数目
     */
    @Column(name = "bedroomNum")
    private String bedroomnum;

    /**
     * 房屋类型
     */
    @Column(name = "houseType")
    private String housetype;

    /**
     * 出租类型
     */
    @Column(name = "rentalType")
    private String rentaltype;

    /**
     * 起租单位
     */
    @Column(name = "rentalPeriodUnit")
    private String rentalperiodunit;

    /**
     * 起租时间
     */
    @Column(name = "rentalPeriod")
    private String rentalperiod;

    /**
     * 价格
     */
    @Column(name = "rentalPrice")
    private BigDecimal rentalprice;
}