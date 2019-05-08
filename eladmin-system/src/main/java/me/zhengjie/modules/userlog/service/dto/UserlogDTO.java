package me.zhengjie.modules.userlog.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Data
public class UserlogDTO implements Serializable {

    private Integer id;

    /**
     * 登陆时间
     */
    private Timestamp loginTime;

    /**
     * ip
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户id
     */
    private Integer userId;
}