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
@TableName("user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /***/
    //@TableId(type = IdType.ASSIGN_ID)
    //@JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


}
