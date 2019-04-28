package me.zhengjie.modules.testlist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Entity
@Data
@Table(name="testlist")
public class Testlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 测试标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 测试内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private Integer cusId;
}