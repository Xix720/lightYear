package com.example.springbootplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootplus.common.CodeMsg;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.entity.DatabaseBak;
import com.example.springbootplus.entity.Menu;
import com.example.springbootplus.entity.User;
import com.example.springbootplus.service.DatabaseBakService;
import com.example.springbootplus.service.IMysqlBackupsService;
import com.example.springbootplus.service.UserService;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/database_bak")
public class DatabaseBakController {
    @Autowired
    private DatabaseBakService databaseBakService;
    @Autowired
    private IMysqlBackupsService mysqlBackupsService;

    //查询数据库备份信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1") Long current, Model model){
        //查询所有数据库备份信息
        IPage<DatabaseBak> pageParam = new Page<>(current,10);
        IPage<DatabaseBak> DatabaseBakPage = databaseBakService.page(pageParam,new QueryWrapper<DatabaseBak>().orderByAsc("id"));
        model.addAttribute("DatabaseBakPage",DatabaseBakPage);
        model.addAttribute("page_title","数据备份");
        //跳转到数据库备份页面
        return "/admin/database_bak/list";

    }

//    //删除数据库备份信息
//    @RequestMapping(value = "/delete",method = RequestMethod.POST)
//    @ResponseBody
//    public Result delete(Long id, HttpSession session){
//        databaseBakService.deleteById(id);
//        //删除数据库备份文件
//        return Result.success(true);
//    }

    //add页面
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        List<DatabaseBak> BakList = databaseBakService.list();
        model.addAttribute("menuGroup",BakList);
        model.addAttribute("page_title","新备份");


        return "/admin/database_bak/add";
    }

    //新建备份
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Result add(HttpServletRequest request){
        DatabaseBak databaseBak = new DatabaseBak();
        String filename=request.getParameter("fileName")+".sql";
        String filepath=request.getParameter("path");
        databaseBak.setCreateTime(LocalDateTime.now());
        databaseBak.setUpdateTime(LocalDateTime.now());

        //校验文件名是否重复
        if(databaseBakService.getByFileName(filename)!=null||filename.equals("")){
            return Result.error(CodeMsg.FILE_NAME_EXIST);
        }

        //添加到数据库中
        databaseBak.setFilename(filename);
        databaseBak.setFilepath(filepath);
        databaseBak.setId(databaseBakService.maxId()+1);
        databaseBakService.save(databaseBak);

        //存储文件到本地路径
        String path =filepath;
        // 数据库用户名
        String userName = "root";
        // 数据库密码
        String password = "root";
        // 数据库ip
        String ip = "localhost:3306/db_springboot_base";
        // 数据库端口号
        String port = "3306";
        // 数据库名称
        String dbname = "db_springboot_base";
        //remark
        String remark = "备份数据库";


        Result r = mysqlBackupsService.mysqlBackups(path, userName, password, ip, port, dbname ,remark);
        System.out.println("success");
        return r;

    }



}

