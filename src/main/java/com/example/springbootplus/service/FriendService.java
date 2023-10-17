package com.example.springbootplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootplus.entity.DatabaseBak;
import com.example.springbootplus.entity.Friend;
import com.example.springbootplus.entity.User;

public interface FriendService extends IService<Friend> {
    //根据id删除好友
    void deleteFriend(long id);
}
