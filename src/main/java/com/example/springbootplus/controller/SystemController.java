package com.example.springbootplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootplus.common.CodeMsg;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.common.bo.LoginUserBO;
import com.example.springbootplus.config.SiteConfig;
import com.example.springbootplus.entity.Menu;
import com.example.springbootplus.entity.OperaterLog;
import com.example.springbootplus.entity.RoleAuthorities;
import com.example.springbootplus.entity.User;
import com.example.springbootplus.service.MenuService;
import com.example.springbootplus.service.OperaterLogService;
import com.example.springbootplus.service.RoleAuthoritiesService;
import com.example.springbootplus.service.UserService;
import com.example.springbootplus.util.LocalDateUtil;
import com.example.springbootplus.validator.LoginValidSequence;
import com.sun.org.apache.bcel.internal.classfile.Code;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/system")
@Slf4j
public class SystemController {
    @Autowired
    private SiteConfig siteConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private OperaterLogService operaterLogService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleAuthoritiesService roleAuthoritiesService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("siteName",siteConfig.getSiteName());
        model.addAttribute("siteUrl",siteConfig.getSiteUrl());
        return "/admin/system/login";
    }

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    @ResponseBody
    public Result<User> login(@Validated({LoginValidSequence.class}) LoginUserBO loginUserBO, Errors errors, HttpSession session){
//    public Result<User> login(@Valid LoginUserBO loginUserBO, Errors errors, HttpSession session){
        log.info("用户名：{}，密码:{}",loginUserBO.getUsername(),loginUserBO.getPassword());
        log.info("验证码：{}",loginUserBO.getCpacha());

        if(errors.hasErrors()){
            errors.getFieldErrors().forEach(fieldError -> {
                System.out.println(fieldError.getField());
            });

            if(errors.getFieldError("username") != null){
                return Result.error(CodeMsg.ADMIN_USERNAME_EMPTY);
            }
            else if(errors.getFieldError("password") != null){
                return Result.error(CodeMsg.ADMIN_PASSWORD_EMPTY);
            }
            else if(errors.getFieldError("cpacha") !=null){
                return Result.error(CodeMsg.CPACHA_EMPTY);
            }
        }

//         //验证验证码是否正确
//        Object sessionAttribute = session.getAttribute("admin_login");
//        if(sessionAttribute == null){
//            return Result.error(CodeMsg.SESSION_EXPIRED);
//        }
//        if(! sessionAttribute.toString().equalsIgnoreCase(loginUserBO.getCpacha())){
//            return Result.error(CodeMsg.CPACHA_ERROR);
//        }


        // 和数据库中的数据比对，判断用户名和密码是否正确
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginUserBO.getUsername())
                .eq("password", loginUserBO.getPassword()));

        if (user == null){
            return Result.error(CodeMsg.ADMIN_LOGIN_ERROR);
        }else if (user.getStatus()==0){ // 判断用户是否被禁用
            return Result.error(CodeMsg.ADMIN_USER_ROLE_UNABLE);

        }else{
            session.removeAttribute("admin_login");
            session.setAttribute("username",loginUserBO.getUsername());
            session.setAttribute("Id",user.getId());

            Long roleId = user.getRoleId();
            QueryWrapper<RoleAuthorities> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id",roleId);
            List<Long> authorityIdList = roleAuthoritiesService.list(queryWrapper).stream().map(RoleAuthorities::getAuthoritiesId).collect(Collectors.toList());
            List<Menu> menuList = menuService.listByIds(authorityIdList);
            List<String> authorityList = menuList.stream().map(Menu::getUrl).collect(Collectors.toList());
            session.setAttribute("authorityList",authorityList);

            Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
            List<Menu> menuLevel1 = menuGroup.get(0L);
            List<Menu> menuLevel2 = new ArrayList<>();
            for (Menu menu : menuLevel1) {
                menuLevel2.addAll(menuGroup.get(menu.getId()));
            }
            session.setAttribute("menuLevel1",menuLevel1);
            session.setAttribute("menuLevel2",menuLevel2);

            OperaterLog operaterLog = new OperaterLog();
            operaterLog.setOperator(user.getUsername());
            operaterLog.setCreateTime(LocalDateTime.now());
            operaterLog.setUpdateTime(LocalDateTime.now());
            String content = "用户【"+user.getUsername()+"】于【" + LocalDateUtil.now() + "】登录系统！";
            operaterLog.setContent(content);

            operaterLogService.save(operaterLog);

            return Result.success(user); //直接返回user是有问题的
        }

    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model,HttpSession session){
        model.addAttribute("siteName",siteConfig.getSiteName());
        model.addAttribute("siteUrl",siteConfig.getSiteUrl());

        IPage<OperaterLog> pageParam = new Page<>(1,10);
        QueryWrapper<OperaterLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        IPage<OperaterLog> page = operaterLogService.page(pageParam, queryWrapper);

        model.addAttribute("operatorLogList",page.getRecords());

        model.addAttribute("operatorLogTotal",page.getTotal());

        return "/admin/system/index";
    }

    @RequestMapping(value = "/no_right",method = RequestMethod.GET)
    public String no_right(){
        return "/admin/error/no_right";
    }
}
