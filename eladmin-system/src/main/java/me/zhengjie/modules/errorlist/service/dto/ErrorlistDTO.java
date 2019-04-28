package me.zhengjie.modules.errorlist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Data
public class ErrorlistDTO implements Serializable {

    private Integer id;

    /**
     * 错误记录
     */
    private String title;

    /**
     * 错误记录内容
     */
    private String content;

    /**
     * 用户id
     */
    private Integer cusId;
}