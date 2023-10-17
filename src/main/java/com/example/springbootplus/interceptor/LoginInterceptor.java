package com.example.springbootplus.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.springbootplus.common.CodeMsg;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        if(username == null){
            //首先判断是否是ajax请求
            String header = request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(header)){
                //表示是ajax请求
                try {
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return false;
            }

            response.sendRedirect("/system/login");
            return false;
        }else{
            return true;
        }
    }
}
