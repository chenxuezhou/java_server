package me.zhengjie.modules.address.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Data
public class AddressDTO implements Serializable {

    private Long id;

    /**
     * 地点名称
     */
    private String name;

    /**
     * 用户id
     */
    private Integer cusId;
}