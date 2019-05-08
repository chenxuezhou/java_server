package me.zhengjie.modules.preview.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Entity
@Data
@Table(name="preview")
public class Preview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 剧名
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 播放地址
     */
    @Column(name = "playurl")
    private String playurl;

    /**
     * 展示图像地址
     */
    @Column(name = "imgurl")
    private String imgurl;
}