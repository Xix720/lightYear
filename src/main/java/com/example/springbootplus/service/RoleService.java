package com.example.springbootplus.service;

import com.example.springbootplus.common.bo.RoleAuthorityBO;
import com.example.springbootplus.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootplus.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
public interface RoleService extends IService<Role> {
    public void save(RoleAuthorityBO roleAuthorityBO);
    public void update(RoleAuthorityBO roleAuthorityBO);
    public List<Role> selectAll();

    List<User> getUserListByRoleId(Long id);
}
