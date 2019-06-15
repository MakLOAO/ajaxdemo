package com.mkl.controller;

import com.alibaba.fastjson.JSON;
import com.mkl.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JSONController {

    @RequestMapping("hellojson")
    public void hellojson(HttpServletResponse response) throws IOException {
        String str = "{\"name\":\"zhangsan\",\"age\":18,\"gender\":\"male\"}"; // {"name":"zhangsan","age":18,"gender":"male"}
        response.getWriter().print(str);
        System.out.println(str);
    }

    // testjson1测试服务器发送JSON数据到前端接收
    // ResponseBody表明返回的是JSON对象，通过转换器转换
    @RequestMapping("/jsontest1")
    @ResponseBody
    public User jsontest1() {
        User user = new User(1, "zs", "123456");
        return user;
    }

    // testjson测试客户端发送JSON数据到服务器接收
    // 接收前端传来的JSON参数，并原样返回给前端
    @RequestMapping("/jsontest")
    @ResponseBody
    public User jsontest(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws IOException {
        return user;
    }
}
