package com.example.springbootplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
//@Getter
//@Setter
@Data
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public User(Long id, String email, String mobile, Integer sex, String username) {
        this.id = id;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.username = username;
    }

    public User() {
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("email")
    private String email;

    @TableField("head_pic")
    private String headPic;

    @TableField("mobile")
    private String mobile;

    @TableField("password")
    private String password;

    @TableField("sex")
    private Integer sex;

    @TableField("status")
    private Integer status;

    @TableField("username")
    private String username;

    @TableField("role_id")
    private Long roleId;

}
