package com.mkl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
