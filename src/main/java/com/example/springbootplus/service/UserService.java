package com.example.springbootplus.service;

import com.example.springbootplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
public interface UserService extends IService<User> {
    public void insert(User user);
    public void update(User user);
    public User findbyid(Long id);
    public void deleteById(Long id);
    public int maxid();
    public User findByName(String name);
    public List<User> fuzzySearch(String name);

    List<User> getUserListByRoleId(Long id);
}
