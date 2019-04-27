package me.zhengjie.modules.collect.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-27
*/
@Entity
@Data
@Table(name="collect")
public class Collect implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 主题
     */
    @Column(name = "topic")
    private String topic;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 收藏
     */
    @Column(name = "pub_id")
    private String pubId;
}