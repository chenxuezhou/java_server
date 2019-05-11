package me.zhengjie.modules.qr_log.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-11
*/
@Data
public class QrLogDTO implements Serializable {

    private Long id;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 参数
     */
    private String params;

    /**
     * 操作用户名
     */
    private String username;
}