package com.example.springbootplus.service.impl;

import com.example.springbootplus.entity.User;
import com.example.springbootplus.mapper.UserMapper;
import com.example.springbootplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    public void Save(User user) {

    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User findbyid(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public int maxid() {
        return userMapper.maxid();
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> fuzzySearch(String name) {
        return userMapper.fuzzySearch(name);
    }

    @Override
    public List<User> getUserListByRoleId(Long id) {
        return userMapper.getUserListByRoleId(id);
    }


}
