package com.webcloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xt
 */
@Data
@TableName("app")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class App implements Serializable {
    /***/
    //@TableId(type = IdType.ASSIGN_ID)
    //@JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 发布时间
     */
    private String time;

    /**
     * 体验次数
     */
    private Integer total;

    /**
     * 体验地址
     */
    private String url;
    /**
     * 图片地址
     */
    private String image;

}
