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

/**
 * <p>
 * 
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Data
@Accessors(chain = true)
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("name")
    private String name;

    @TableField("url")
    private String url;

    @TableField("icon")
    private String icon;

    @TableField("sort")
    private Integer sort;

    @TableField("parent_id")
    private Long parentId;

    @TableField("is_button")
    private Boolean isButton;

    @TableField("is_show")
    private Boolean isShow;


}
