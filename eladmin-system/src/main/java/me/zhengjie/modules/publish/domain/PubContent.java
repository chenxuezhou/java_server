package me.zhengjie.modules.publish.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-26
*/
@Entity
@Data
@Table(name="pub_content")
public class PubContent implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 资源id
     */
    @Column(name = "res_id")
    private String resId;

    /**
     * 主题
     */
    @Column(name = "topic")
    private String topic;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;
}