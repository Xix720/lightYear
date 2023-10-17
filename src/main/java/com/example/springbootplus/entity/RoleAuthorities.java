package com.example.springbootplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Getter
@Setter
@Accessors(chain = true)
@TableName("role_authorities")
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthorities implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("role_id")
    private Long roleId;

    @TableField("authorities_id")
    private Long authoritiesId;


}
