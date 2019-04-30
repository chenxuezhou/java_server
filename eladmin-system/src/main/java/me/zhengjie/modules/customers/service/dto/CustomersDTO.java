package me.zhengjie.modules.customers.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-29
*/
@Data
public class CustomersDTO implements Serializable {

    private Long id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 密码
     */
    private String password;

    /**
     * 默认地址
     */
    private String addressId;
}