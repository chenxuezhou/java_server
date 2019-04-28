package me.zhengjie.modules.testlist.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author jie
* @date 2019-04-28
*/
@Data
public class TestlistDTO implements Serializable {

    private Integer id;

    /**
     * 测试标题
     */
    private String title;

    /**
     * 测试内容
     */
    private String content;

    /**
     * 用户id
     */
    private Integer cusId;
}