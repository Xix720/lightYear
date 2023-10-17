package com.example.springbootplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootplus.entity.DatabaseBak;
import com.example.springbootplus.entity.Friend;
import com.example.springbootplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendMapper extends BaseMapper<Friend> {
    void deleteFriend(long id);
}
