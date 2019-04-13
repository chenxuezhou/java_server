package me.zhengjie.modules.meet.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-13
*/
@Data
public class MeetingDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 创建人用户id
     */
    private String userId;

    /**
     * 会议时间
     */
    private Timestamp createTime;

    /**
     * 会议主持人
     */
    private String hostPer;

    /**
     * 会议出席人
     */
    private String outPer;

    /**
     * 会议地点
     */
    private String address;

    /**
     * 主题
     */
    private String topic;

    /**
     * 内容
     */
    private String content;
}