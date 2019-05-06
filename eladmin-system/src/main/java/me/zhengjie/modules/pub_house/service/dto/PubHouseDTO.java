package me.zhengjie.modules.pub_house.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-04
*/
@Data
public class PubHouseDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 卫浴数目
     */
    private String toiletnum;

    /**
     * 客厅数目
     */
    private String parlournum;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 资源id
     */
    private String cover;

    /**
     * 名称
     */
    private String name;

    /**
     * 城市
     */
    private String area;

    /**
     * 用户id
     */
    private String cusId;

    /**
     * 居室数目
     */
    private String bedroomnum;

    /**
     * 房屋类型
     */
    private String housetype;

    /**
     * 出租类型
     */
    private String rentaltype;

    /**
     * 起租单位
     */
    private String rentalperiodunit;

    /**
     * 起租时间
     */
    private String rentalperiod;

    /**
     * 价格
     */
    private BigDecimal rentalprice;
}