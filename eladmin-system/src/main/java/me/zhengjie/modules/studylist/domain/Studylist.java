package me.zhengjie.modules.studylist.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Entity
@Data
@Table(name="studylist")
public class Studylist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 笔记标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 笔记内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 用户id
     */
    @Column(name = "cus_id")
    private Integer cusId;
}