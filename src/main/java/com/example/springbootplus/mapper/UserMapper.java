package com.example.springbootplus.mapper;

import com.example.springbootplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface UserMapper extends BaseMapper<User> {
    public void update(User user);
    public User findById(Long id);
    public void add(User user);
    public List<User> selectByPage(int page, int size); //分页查询
    public int counts(); //查询总数
    public int maxid(); //查询最大id
    public User findByName(String name); //查询用户名是否存在
    public List<User> fuzzySearch(String name); //模糊查询

    List<User> getUserListByRoleId(Long id);
}
