package me.zhengjie.modules.qr_log.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-11
*/
@Entity
@Data
@Table(name="qr_log")
public class QrLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    /**
     * 参数
     */
    @Column(name = "params")
    private String params;

    /**
     * 操作用户名
     */
    @Column(name = "username")
    private String username;
}