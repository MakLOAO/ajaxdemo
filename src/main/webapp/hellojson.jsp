<%--
  Created by IntelliJ IDEA.
  User: MakaLoo
  Date: 2019/6/14
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" >
        function createXMLHttpRequest() {
            try {
                return new XMLHttpRequest();
            } catch (e) {
                try {
                    return new ActiveXObject("Msxml2.XMLHTTP");
                } catch (e) {
                    try {
                        new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                        throw e;
                    }
                }
            }
        }

        window.onload = function (ev) {
            var btn = document.getElementById("btn");
            btn.onclick = function (ev1) {
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("GET", "/hellojson.do", true);
                xmlHttp.send(null);
                xmlHttp.onreadystatechange = function () { // xmlHttp的任意一种状态变化都会调用本方法
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) { // 状态码200表示服务器已成功处理了请求
                        var text = xmlHttp.responseText; // 返回的是一个json字符串
                        var person = eval("(" + text + ")"); // eval函数是用JavaScript执行其参数的内容，需要注意的是要加圆括号括起来
                        var s = person.name + ", " + person.age + ", " + person.gender;
                        var h1 = document.getElementById("h1");
                        h1.innerText = s;
                    }
                }
            }
        }
    </script>
</head>
<body>
    <button id="btn">click here</button>
    <h1 id="h1"></h1>
</body>
</html>
