package me.zhengjie.modules.user_opt.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-14
*/
@Data
public class UsersDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;
}