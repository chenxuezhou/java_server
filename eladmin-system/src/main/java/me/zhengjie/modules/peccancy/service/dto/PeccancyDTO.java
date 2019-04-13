package me.zhengjie.modules.peccancy.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-03-12
*/
@Data
public class PeccancyDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * ��������
     */
    private Timestamp createTime;

    /**
     * ����
     */
    private String title;

    /**
     * ��������
     */
    private String content;

    /**
     * ���
     */
    private String number;
}