package com.example.springbootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootplus.common.bo.RoleAuthorityBO;
import com.example.springbootplus.entity.Role;
import com.example.springbootplus.entity.RoleAuthorities;
import com.example.springbootplus.entity.User;
import com.example.springbootplus.mapper.RoleAuthoritiesMapper;
import com.example.springbootplus.mapper.RoleMapper;
import com.example.springbootplus.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleAuthoritiesMapper roleAuthoritiesMapper;

    @Transactional
    @Override
    public void save(RoleAuthorityBO roleAuthorityBO) {
        Role role = roleAuthorityBO.getRole();

        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(role);

        roleAuthorityBO.getAuthorityId().forEach(authorityId ->{
            roleAuthoritiesMapper.insert(new RoleAuthorities(role.getId(), authorityId));
        });
    }

    @Transactional
    @Override
    public void update(RoleAuthorityBO roleAuthorityBO) {
        Role role = roleAuthorityBO.getRole();
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(role);

        QueryWrapper<RoleAuthorities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",role.getId());
        roleAuthoritiesMapper.delete(queryWrapper);

        roleAuthorityBO.getAuthorityId().forEach(authorityId ->{
            roleAuthoritiesMapper.insert(new RoleAuthorities(role.getId(), authorityId));
        });
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public List<User> getUserListByRoleId(Long id) {
        return roleMapper.getUserListByRoleId(id);
    }

    @Transactional
    @Override
    public boolean removeById(Serializable id) {
        QueryWrapper<RoleAuthorities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        roleAuthoritiesMapper.delete(queryWrapper);

        roleMapper.deleteById(id);

        return super.removeById(id);
    }
}
