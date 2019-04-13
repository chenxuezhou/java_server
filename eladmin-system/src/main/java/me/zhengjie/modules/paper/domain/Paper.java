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
     * 创建日期
     */
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 标题
     */
    @Column(name = "title",nullable = false)
    private String title;

    /**
     * 主题内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 论文链接
     */
    @Column(name = "link")
    private String link;

    /**
     * 编号
     */
    @Column(name = "number")
    private String number;
}