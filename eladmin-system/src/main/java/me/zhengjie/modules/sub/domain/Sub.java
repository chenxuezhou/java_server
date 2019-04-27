package me.zhengjie.modules.sub.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-27
*/
@Entity
@Data
@Table(name="sub")
public class Sub implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 订阅
     */
    @Column(name = "dinyue")
    private Integer dinyue;

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
    @Column(name = "soucan")
    private Integer soucan;
}