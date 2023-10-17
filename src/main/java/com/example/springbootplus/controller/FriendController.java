package com.example.springbootplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootplus.common.CodeMsg;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.entity.DatabaseBak;
import com.example.springbootplus.entity.Friend;
import com.example.springbootplus.entity.User;
import com.example.springbootplus.service.FriendService;
import com.example.springbootplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;
    //list页面
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1") Long current, Model model, HttpSession session){
        //查询当前用户所有好友的信息
        IPage<Friend> pageParam = new Page<>(current,12);
//        IPage<Friend> FriendPage = friendService.page(pageParam,new QueryWrapper<Friend>().orderByAsc("adderId"));
        //选出当前用户的好友信息
        String userid = session.getAttribute("Id").toString();
        System.out.println(userid);
        IPage<Friend> FriendPage = friendService.page(pageParam,new QueryWrapper<Friend>().eq("adderId",userid));
        //获取FriendPage中第一条记录的adderId，并通过adderId查询出adder的信息
        List<Friend> FriendList = FriendPage.getRecords();
        List<User> UserList = new ArrayList<>();
        for (Friend friend : FriendList) {
            User user = userService.getById(friend.getFriendId());
            UserList.add(user);
        }
        System.out.println(UserList);
        model.addAttribute("FriendPage",FriendPage);
        model.addAttribute("page_title","我的好友");
        return "/admin/friend/list";
    }

    //用户信息界面
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public String info(@PathVariable long id, Model model){
        User thisuser = userService.getById(id);
        model.addAttribute("userinfo",thisuser);
        model.addAttribute("page_title","用户信息");
        return "/admin/friend/info";
    }

    //添加黑名单界面
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("page_title","添加好友");
        //分页筛选已启用的用户
        IPage<User> pageParam = new Page<>(1,12);
        IPage<User> userPage = userService.page(pageParam,new QueryWrapper<User>().eq("status",1));
        model.addAttribute("userPage",userPage);

        return "/admin/friend/add";
    }

    //添加黑名单
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(long friendId, HttpSession session){
        //获取当前用户的id
        String userid = session.getAttribute("Id").toString();
        //转换为long
        long useridlong = Long.parseLong(userid);

        //获取用户的姓名以及头像
        User user = userService.getById(friendId);
        //不能将自己添加到黑名单中
        if(useridlong == friendId){
            return Result.error(CodeMsg.BLACK_LIST_CANT_SELF);
        }
        //添加黑名单
        Friend newFriend = new Friend();
        newFriend.setAdderid(useridlong);
        newFriend.setFriendId(friendId);
        newFriend.setFriendName(user.getUsername());
        newFriend.setFriend_headpic(user.getHeadPic());
        friendService.save(newFriend);

        ////将数据库中用户的状态改为0
        user.setStatus(0);
        userService.updateById(user);
        return Result.success(true);
    }

    //删除黑名单
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(long id, Model model){
        System.out.println(id);
        friendService.deleteFriend(id);
        model.addAttribute("page_title","删除黑名单");
        //将数据库中用户的状态改为1
        User user = userService.getById(id);
        user.setStatus(1);
        userService.updateById(user);
        return Result.success(true);
    }
}
