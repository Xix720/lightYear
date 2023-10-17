package com.example.springbootplus.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootplus.common.CodeMsg;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.entity.*;
import com.example.springbootplus.entity.common.UUIDUtil;
import com.example.springbootplus.service.MenuService;
import com.example.springbootplus.service.RoleService;
import com.example.springbootplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleservice;

    public String PicUrlResource;


//    //list页面
//    @RequestMapping(value = "/list" , method = RequestMethod.GET)
//    public String list(Model model){
//        model.addAttribute("userlist",userService.list());
//        model.addAttribute("page_title","用户管理");
//        return "/admin/user/list";
//    }
    //add页面
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        List<Menu> menuList = menuService.list();

        Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        model.addAttribute("menuGroup",menuGroup);
        model.addAttribute("usertype",roleservice.selectAll());
        model.addAttribute("page_title","新增用户");


        return "/admin/user/add";
    }

    //新增用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(HttpSession session,HttpServletRequest request, HttpServletResponse response){
        System.out.println("新增用户");
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setId((long) (userService.maxid()+1));
        user.setHeadPic("avatar.jpg");
        user.setUsername(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setRoleId((long) Integer.parseInt(request.getParameter("type")));
        user.setStatus(Integer.valueOf(request.getParameter("status")));
        user.setEmail(request.getParameter("email"));
        user.setMobile(request.getParameter("phone"));
        user.setSex(Integer.valueOf(request.getParameter("sex")));
        //判断用户名是否重复
        if(userService.findByName(user.getUsername())!=null) {
            //返回错误信息
            return Result.error(CodeMsg.ADMIN_USERNAME_EXIST);
        }else{
            userService.insert(user);
            return Result.success(true);
        }

    }
    //编辑用户页面
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model){
        System.out.println(id);
        User thisuser = userService.getById(id);
        model.addAttribute("userinfo",thisuser);
        model.addAttribute("page_title","编辑用户信息");
        return "/admin/user/edit";
    }

    //用户信息界面
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public String info(@PathVariable long id, Model model){
        User thisuser = userService.getById(id);
        model.addAttribute("userinfo",thisuser);
        model.addAttribute("page_title","用户信息");
        return "/admin/user/info";
    }
    //修改用户信息
    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String update(@RequestBody User user){
        User tempuser = userService.findbyid(user.getId());
        tempuser.setEmail(user.getEmail());
        tempuser.setUsername(user.getUsername());
        tempuser.setMobile(user.getMobile());
        tempuser.setSex(user.getSex());
//        System.out.println(this.PicUrlResource);
        tempuser.setHeadPic(this.PicUrlResource);
//        System.out.println(tempuser);
        userService.update(tempuser);
        return "redirect:/user/list";
    }

    //删除用户信息

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long id,HttpSession session){
        userService.deleteById(id);
        return Result.success(true);
    }

    //分页查询用户信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1") Long current, Model model){
        IPage<User> pageParam = new Page<>(current,10);
        IPage<User> userPage = userService.page(pageParam,new QueryWrapper<User>().orderByAsc("id"));
        model.addAttribute("userPage",userPage);
        model.addAttribute("page_title","用户管理");


        return "/admin/user/list";
    }

    //接收前台传过来的关键字
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public String search(@RequestParam String keyword, Model model){
        System.out.println("get"+keyword);
        IPage<User> pageParam = new Page<>(1,10);
        IPage<User> userPage = userService.page(pageParam,new QueryWrapper<User>().like("username",keyword).orderByAsc("id"));
        userPage.getRecords().forEach(System.out::println);
        model.addAttribute("userPage",userPage);
        model.addAttribute("page_title","用户管理");
        System.out.println("complete");
        return "/admin/user/list";

    }

    //上传图片
    @RequestMapping(value = "/uploadPic",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestPart(name = "file", required = false) MultipartFile file, HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        JSONObject jsonObject = new JSONObject();
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        // 拼接文件上传路径
        String savePath = "E:/2022企业实训/项目1/src/main/resources/static/images/users/";
        System.out.println(savePath);
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdirs();
        }
        UUIDUtil UuidUtil = new UUIDUtil();
        String filename = UuidUtil.getCharAndNumr(32) + "." + suffix; // 文件重命名
        this.PicUrlResource = filename;
        try {
            //将文件保存指定目录
            File filePath = new File(savePath + filename);
            file.transferTo(filePath);
        } catch (Exception e) {
            jsonObject.put("type", "error");
            jsonObject.put("msg", "上传失败！");
            e.printStackTrace();
            return jsonObject;
        }
        //判断文件后缀名是否为jpg或png
        if(!suffix.equals("jpg") && !suffix.equals("png")){
            jsonObject.put("type", "error");
            jsonObject.put("msg", "上传失败！");
            return jsonObject;
        }
        jsonObject.put("type", "success");
        jsonObject.put("msg", "上传图片成功！");
        return jsonObject;
    }




}

