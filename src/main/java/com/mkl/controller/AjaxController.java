package com.mkl.controller;

import com.mkl.entity.User;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AjaxController {

    @RequestMapping("/hello")
    public void hello(HttpServletResponse response) throws IOException {
        response.getWriter().print("Hello, Ajax!!");
        System.out.println("Hello Ajax!!");
    }

    // 处理POST请求，前端发来带参数的POST请求，负责处理该参数
    @RequestMapping("/post")
    public void post(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        response.setContentType("text/html;charset=utf-8"); // 处理响应编码问题
        request.setCharacterEncoding("utf-8"); // 处理请求编码问题
        System.out.println(user);
        response.getWriter().print("Post：" + user);
    }

    // 校验表单用户是否被注册，如果用户名为admin，则返回1，否则返回0
    @RequestMapping("/validate")
    public void validate(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        response.setContentType("text/html;charset=utf-8"); // 处理响应编码问题
        request.setCharacterEncoding("utf-8"); // 处理请求编码问题
        String name = user.getName();
        if (name.equalsIgnoreCase("admin")) {
            response.getWriter().print("1");
        } else response.getWriter().print("0");
    }

    // 返回XML对象
    @RequestMapping("/xml")
    public void xml(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        response.setContentType("text/html;charset=utf-8"); // 处理响应编码问题
        request.setCharacterEncoding("utf-8"); // 处理请求编码问题
        String xml = "<students>" +
                "<student number='ITCAST_1001'>" +
                "<name>zhangsan</name>" +
                "<age>18</age>" +
                "<gender>male</gender>" +
                "</student>" +
                "</students>";
        response.setContentType("text/xml;charset=utf-8");
        response.getWriter().print(xml);
    }

    // 响应所有省份名称，使用逗号分隔
    @RequestMapping("/province")
    public void province(HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("text/html;charset=utf-8"); // 处理响应编码问题
        request.setCharacterEncoding("utf-8"); // 处理请求编码问题
        SAXReader reader = new SAXReader();
        InputStream inputStream = this .getClass().getResourceAsStream("/china.xml");
        Document doc = reader.read(inputStream);
        Element root = doc.getRootElement(); // 获取根节点
        List<Element> arrList = root.elements(); // 获取所有根节点的下属节点
        List<String> attrList = new ArrayList<>();
        for (int i = 0; i < arrList.size(); i++) {
            attrList.add(arrList.get(i).attributeValue("name")); // 获取属性name的值
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrList.size(); i++) {
            sb.append(attrList.get(i));
            if (i < arrList.size() - 1) {
                sb.append(",");
            }
        }
        response.getWriter().print(sb);
    }

    // 获取省份名称，加载该省对应的<province>元素，把元素转换为字符串发送给客户端
    @RequestMapping("/city")
    public void city(HttpServletRequest request, HttpServletResponse response, String pname) throws IOException, DocumentException {
        response.setContentType("text/html;charset=utf-8"); // 处理响应编码问题
        request.setCharacterEncoding("utf-8"); // 处理请求编码问题
        pname = URLDecoder.decode(pname, "utf-8");
        SAXReader reader = new SAXReader();
        InputStream inputStream = this .getClass().getResourceAsStream("/china.xml");
        Document doc = reader.read(inputStream);
        Element root = doc.getRootElement(); // 获取根节点
        Iterator<Element> it = root.elementIterator();
        while (it.hasNext()) {
            Element province = it.next();
            if (province.attribute("name").getValue().equals(pname)) {
                String xmlStr = province.asXML();
                // 返回的内容为xml时，一定要添加下面的内容，然后在前端获取时var doc = xmlHttp.responseXML;
                response.setContentType("text/xml;charset=utf-8");
                response.getWriter().print(xmlStr);
            }
        }
    }
}
