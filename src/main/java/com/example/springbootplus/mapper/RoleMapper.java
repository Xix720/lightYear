package com.example.springbootplus.mapper;

import com.example.springbootplus.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    public List<Role> selectAll();

    List<User> getUserListByRoleId(Long id);
}
