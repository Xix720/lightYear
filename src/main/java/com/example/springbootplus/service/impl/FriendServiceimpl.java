package com.example.springbootplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootplus.entity.Friend;
import com.example.springbootplus.entity.User;
import com.example.springbootplus.mapper.FriendMapper;
import com.example.springbootplus.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceimpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
    @Autowired
    private FriendMapper friendMapper;

    @Override
    public void deleteFriend(long id) {
        friendMapper.deleteFriend(id);
    }
}
