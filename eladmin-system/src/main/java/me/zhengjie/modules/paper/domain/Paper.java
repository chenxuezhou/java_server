package me.zhengjie.modules.paper.domain;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author jie
* @date 2019-03-12
*/
@Entity
@Data
@Table(name="paper")
public class Paper implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * ��������
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * ����
     */
    @Column(name = "title",nullable = false)
    private String title;

    /**
     * ��������
     */
    @Column(name = "content")
    private String content;

    /**
     * ��������
     */
    @Column(name = "link")
    private String link;

    /**
     * ���
     */
    @Column(name = "number")
    private String number;
}