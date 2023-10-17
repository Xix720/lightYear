package com.example.springbootplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootplus.common.CodeMsg;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.common.bo.RoleAuthorityBO;
import com.example.springbootplus.common.vo.RoleAuthorityVO;
import com.example.springbootplus.entity.Menu;
import com.example.springbootplus.entity.Role;
import com.example.springbootplus.entity.RoleAuthorities;
import com.example.springbootplus.entity.User;
import com.example.springbootplus.service.MenuService;
import com.example.springbootplus.service.RoleAuthoritiesService;
import com.example.springbootplus.service.RoleService;
import com.example.springbootplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    UserService userService;
    @Autowired
    RoleAuthoritiesService roleAuthoritiesService;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(Model model){
//
//        model.addAttribute("roleList",roleService.list());
//        model.addAttribute("page_title","角色管理");
//
//        return "/admin/role/list";
//    }

    //分页查询
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1") Long current, Model model){
        IPage<Role> pageParam = new Page<>(current,10);
        IPage<Role> rolePage = roleService.page(pageParam,new QueryWrapper<Role>().orderByAsc("id"));
        model.addAttribute("rolePage",rolePage);
        model.addAttribute("page_title","角色管理");


        return "/admin/role/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        List<Menu> menuList = menuService.list();

        Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        model.addAttribute("menuGroup",menuGroup);
        model.addAttribute("page_title","新增角色");

        return "/admin/role/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(RoleAuthorityBO roleAuthorityBO){

        // TODO : 验证BO

        try {
            roleService.save(roleAuthorityBO);
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.error(CodeMsg.ADMIN_ROLE_ADD_ERROR);
        }

        // TODO： 记录日志

        return Result.success(true);
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Long id,Model model){
        Role role = roleService.getById(id);

        QueryWrapper<RoleAuthorities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        List<RoleAuthorities> roleAuthoritiesList = roleAuthoritiesService.list(queryWrapper);

        RoleAuthorityVO roleAuthorityVO = new RoleAuthorityVO();
        roleAuthorityVO.setRole(role);

        List<Long> authorityIdList = new ArrayList<>();
        roleAuthoritiesList.forEach(roleAuthorities ->
                authorityIdList.add(roleAuthorities.getAuthoritiesId())
        );

        roleAuthorityVO.setAuthorityId(authorityIdList);
        model.addAttribute("roleAuthorityVO",roleAuthorityVO);

        List<Menu> menuList = menuService.list();

        Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        model.addAttribute("menuGroup",menuGroup);
        model.addAttribute("page_title","角色权限编辑");

        return "/admin/role/edit";
    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    @ResponseBody
    public Result edit(RoleAuthorityBO roleAuthorityBO){

        // TODO : 验证BO

        try{
            roleService.update(roleAuthorityBO);
            //同时修改所有对应用户的状态
            List<User> userList = userService.getUserListByRoleId(roleAuthorityBO.getRole().getId());
            userList.forEach(user -> {
                user.setStatus(roleAuthorityBO.getRole().getStatus());
                userService.updateById(user);
            });
        } catch (Exception ex){
            ex.printStackTrace();
            return Result.error(CodeMsg.ADMIN_MENU_EDIT_ERROR);
        }

        // TODO： 记录日志
        return Result.success(true);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id,Model model){
        Role role = roleService.getById(id);

        QueryWrapper<RoleAuthorities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        List<RoleAuthorities> roleAuthoritiesList = roleAuthoritiesService.list(queryWrapper);

        RoleAuthorityVO roleAuthorityVO = new RoleAuthorityVO();
        roleAuthorityVO.setRole(role);

        List<Long> authorityIdList = new ArrayList<>();
        roleAuthoritiesList.forEach(roleAuthorities ->
                authorityIdList.add(roleAuthorities.getAuthoritiesId())
        );

        roleAuthorityVO.setAuthorityId(authorityIdList);
        model.addAttribute("roleAuthorityVO",roleAuthorityVO);

        List<Menu> menuList = menuService.list();

        Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        model.addAttribute("menuGroup",menuGroup);

        return "/admin/role/delete";
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long id){

        // TODO : 检查角色Id是否是某个用户的外键

//        System.out.println(id);

        try {
            roleService.removeById(id);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            return Result.error(CodeMsg.ADMIN_ROLE_DELETE_ERROR);
        }

        return Result.success(true);
    }
}


