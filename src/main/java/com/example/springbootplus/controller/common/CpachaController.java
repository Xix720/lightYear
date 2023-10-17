package com.example.springbootplus.controller.common;

import com.example.springbootplus.util.CpachaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/cpacha")
@Slf4j
public class CpachaController {

    @RequestMapping(value = "/generate_cpacha", method = RequestMethod.GET)
    public void generateCpacha(
            @RequestParam(name = "vl", defaultValue = "4") Integer vcodeLength,//vcodeLength,验证码长度
            @RequestParam(name = "fs", defaultValue = "21") Integer fontSize,//fontSize,验证码字体大小
            @RequestParam(name = "w", defaultValue = "98") Integer width,//width,图片宽度
            @RequestParam(name = "h", defaultValue = "33") Integer height,//height,图片高度
            @RequestParam(name = "method", defaultValue = "admin_login") String method,//用来调用此方法的名称，以此名称为键，存入到session中
            HttpServletResponse response,
            HttpSession session) {
        CpachaUtil cpachaUtil = new CpachaUtil(vcodeLength, fontSize, width, height);
        String generatorVCode = cpachaUtil.generatorVCode();
        //将生成的验证码放入session，以供放后面程序的验证使用
        session.setAttribute(method, generatorVCode);
        log.info("验证码成功生成，method=" + method + ",value=" + generatorVCode);
        try {
            ImageIO.write(cpachaUtil.generatorRotateVCodeImage(generatorVCode, true), "gif", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //校验验证码
    @RequestMapping(value = "/check_cpacha", method = RequestMethod.GET)
    public void checkCpacha(
            @RequestParam(name = "vl", defaultValue = "4") Integer vcodeLength,//vcodeLength,验证码长度
            @RequestParam(name = "fs", defaultValue = "21") Integer fontSize,//fontSize,验证码字体大小
            @RequestParam(name = "w", defaultValue = "98") Integer width,//width,图片宽度
            @RequestParam(name = "h", defaultValue = "33") Integer height,//height,图片高度
            @RequestParam(name = "method", defaultValue = "admin_login") String method,//用来调用此方法的名称，以此名称为键，存入到session中
            @RequestParam(name = "vcode", defaultValue = "") String vcode,//用户输入的验证码
            HttpSession session, HttpServletResponse response) throws IOException {
        String generatorVCode = (String) session.getAttribute(method);
        if (generatorVCode == null) {
            try {
                response.getWriter().write("{\"code\":\"-1\",\"msg\":\"验证码已过期\"}");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            if (generatorVCode.equals(vcode)) {
                try {
                    response.getWriter().write("{\"code\":\"0\",\"msg\":\"验证码正确\"}");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                try {
                    response.getWriter().write("{\"code\":\"-2\",\"msg\":\"验证码错误\"}");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}