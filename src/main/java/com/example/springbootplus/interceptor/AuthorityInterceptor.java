package com.example.springbootplus.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.springbootplus.common.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        logger.info("请求路径："+ requestURI);

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("authorityList");
        if(obj != null){
            List<String> authorityList = (List<String>) obj;
            boolean present = authorityList.stream().filter(url -> url.equals("") ? false : requestURI.indexOf(url)>=0).findFirst().isPresent();

            if(present == true){
                logger.info("权限验证通过");
                return true;
            }

            //首先判断是否是ajax请求
            String header = request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(header)){
                //表示是ajax请求
                try {
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(JSON.toJSONString(CodeMsg.ADMIN_NO_RIGHT));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return false;
            }
        }

        response.sendRedirect("/system/no_right");
        return false;
    }
}
