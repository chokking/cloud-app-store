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
*   
*   @author xt
*/
@Data
@TableName("comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /***/
        //@TableId(type = IdType.ASSIGN_ID)
        //@JsonSerialize(using = ToStringSerializer.class)
        @TableId(type = IdType.AUTO)
        private Integer id;

    /**app编号*/
        private Integer appId;

    /**评论用户*/
        private String username;

    /**评论时间*/
        private String time;

    /**评论内容*/
        private String content;


}
