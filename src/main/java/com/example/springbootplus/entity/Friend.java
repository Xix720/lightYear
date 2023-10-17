package com.example.springbootplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@TableName("friend")
public class Friend {
    @TableId(value = "adderid", type = IdType.AUTO)
    private Long adderid;

    @TableField("friendId")
    private Long friendId;

    @TableField("friendname")
    private String friendName;

    @TableField("friend_headpic")
    private String friend_headpic;

}
