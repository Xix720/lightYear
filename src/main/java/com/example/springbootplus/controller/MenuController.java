package com.example.springbootplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootplus.common.CodeMsg;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.entity.Menu;
import com.example.springbootplus.entity.User;
import com.example.springbootplus.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1")Long current,Model model){
        List<Menu> menuList = menuService.list();

//        Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));

//        menuGroup.keySet().forEach(parentId -> {
//            System.out.println(parentId);
//            menuGroup.get(parentId).forEach(System.out::println);
//            System.out.println("=============================");
//        });


        Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        model.addAttribute("menuGroup",menuGroup);
        model.addAttribute("page_title","菜单管理");

        return "/admin/menu/list";
    }

//    //分页查询菜单信息
//    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    public String list(@RequestParam(defaultValue = "1") Long current, Model model){
//        IPage<Menu> pageParam = new Page<>(current,10);
//        IPage<Menu> menuPage = menuService.page(pageParam,new QueryWrapper<Menu>().orderByAsc("parent_id"));
//        List<Menu> menuList = menuService.list();
//        Map<Long, List<Menu>> menuGroup = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
//        model.addAttribute("menuPage",menuPage);
//        model.addAttribute("menuGroup",menuGroup);
//        model.addAttribute("page_title","菜单管理");
//
//
//        return "/admin/menu/list";
//    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        List<Menu> menuList = menuService.findByLevel1AndLevel2();

        model.addAttribute("menuList",menuList);

        return "/admin/menu/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(Menu menu, HttpSession session){

        // ToDo:服务端请求参数验证

        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());
        menuService.save(menu);

        session.setAttribute("menuLevel1",menuService.findByLevel1());
        session.setAttribute("menuLevel2",menuService.findByLevel2());

        // ToDo: 日志记录

        return Result.success(true);
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model){

        Menu editMenu = menuService.getById(id);
        model.addAttribute("editMenu",editMenu);

        // 判断是否有子元素
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        List<Menu> childMenuList = menuService.list(queryWrapper); //所有下级子菜单
        if (childMenuList == null || childMenuList.size() == 0){ // 没有子菜单的一级菜单，二级菜单，三级菜单
            List<Menu> menuLevel1AndLevel2 = menuService.findByLevel1AndLevel2();
            menuLevel1AndLevel2.remove(editMenu);
            model.addAttribute("menuList",menuLevel1AndLevel2);
        }else{ //可能是有下级子菜单的一级或二级菜单
            if(editMenu.getParentId() == 0){ // menu是有下级菜单的一级菜单
                //有两级子菜单的一级菜单
                boolean result = menuService.findByLevel3().stream().anyMatch(
                        menuLevel3 -> childMenuList.stream().anyMatch(
                                childMenu -> childMenu.getId() == menuLevel3.getParentId()
                        )
                );
                if (result){
                    return "/admin/menu/edit";
                }
            }
            //menu是有子菜单的二级菜单
            //只有一级子菜单的一级菜单
            List<Menu> menuLevel1 = menuService.findByLevel1();
            menuLevel1.remove(editMenu);
            model.addAttribute("menuList",menuLevel1);
        }

        return "/admin/menu/edit";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Result edit(Menu menu,HttpSession session){
        System.out.println(menu);

        // ToDo:服务端请求参数验证

        menu.setUpdateTime(LocalDateTime.now());
        menuService.updateById(menu);

//        session.setAttribute("menuLevel1",menuService.findByLevel1());
//        session.setAttribute("menuLevel2",menuService.findByLevel2());

        //TODO:修改application中token

        // ToDo: 日志记录

        return Result.success(true);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long id,HttpSession session){

        // ToDo:服务端请求参数验证

        // TODO: 检查菜单id是否已经和某个角色关联

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",id);
        long count = menuService.count(queryWrapper);

        if(count > 0){
            return Result.error(CodeMsg.ADMIN_MENU_DELETE_ERROR);
        }

        menuService.removeById(id);

//        session.setAttribute("menuLevel1",menuService.findByLevel1());
//        session.setAttribute("menuLevel2",menuService.findByLevel2());

        //TODO:修改application中token

        // ToDo: 日志记录

        return Result.success(true);
    }
}

