package me.zhengjie.modules.technology.domain;

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
@Table(name="technology")
public class Technology implements Serializable {

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
     * ���
     */
    @Column(name = "number")
    private String number;
}