package me.zhengjie.modules.preview.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-05-07
*/
@Data
public class PreviewDTO implements Serializable {

    private Integer id;

    /**
     * 剧名
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 播放地址
     */
    private String playurl;

    /**
     * 展示图像地址
     */
    private String imgurl;
}