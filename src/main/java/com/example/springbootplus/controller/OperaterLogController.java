package com.example.springbootplus.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.entity.OperaterLog;
import com.example.springbootplus.service.OperaterLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Controller
@RequestMapping("/log")
public class OperaterLogController {
    @Autowired
    private OperaterLogService operaterLogService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1") Long current, Model model){

        IPage<OperaterLog> pageParam = new Page<>(current,10);
        IPage<OperaterLog> logPage = operaterLogService.page(pageParam,new QueryWrapper<OperaterLog>().orderByDesc("id"));

        model.addAttribute("logPage",logPage);
        model.addAttribute("page_title","日志管理");

        return "/admin/log/list";
    }

    //删除选中日志
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids)throws IOException {
        //输出ids中的所有id
        for (Long id : ids) {
            System.out.println(id);
        }
        operaterLogService.removeByIds(Arrays.asList(ids));
        return Result.success(true);
    }

    //删除全部日志
    @RequestMapping(value = "/delete_all",method = RequestMethod.POST)
    @ResponseBody
    public Result delete_all()throws IOException {
        operaterLogService.remove(null);
        return Result.success(true);
    }

}

