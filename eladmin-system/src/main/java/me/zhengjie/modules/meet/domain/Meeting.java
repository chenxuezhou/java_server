package me.zhengjie.modules.meet.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-13
*/
@Entity
@Data
@Table(name="meeting")
public class Meeting implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 创建人用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 会议时间
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 会议主持人
     */
    @Column(name = "host_per")
    private String hostPer;

    /**
     * 会议出席人
     */
    @Column(name = "out_per")
    private String outPer;

    /**
     * 会议地点
     */
    @Column(name = "address")
    private String address;

    /**
     * 主题
     */
    @Column(name = "topic")
    private String topic;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;
}