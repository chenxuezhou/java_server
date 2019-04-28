package me.zhengjie.modules.errorlist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Entity
@Data
@Table(name="errorlist")
public class Errorlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 错误记录
     */
    @Column(name = "title")
    private String title;

    /**
     * 错误记录内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private Integer cusId;
}