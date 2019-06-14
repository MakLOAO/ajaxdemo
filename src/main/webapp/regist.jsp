<%--
  Created by IntelliJ IDEA.
  User: MakaLoo
  Date: 2019/6/14
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
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
            // 获取文本框，为它的失去焦点事件注册监听
            var userEle = document.getElementById("usernameEle");
            userEle.onblur = function (ev1) {
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("POST", "/validate.do", true);
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlHttp.send("name=" + userEle.value);
                xmlHttp.onreadystatechange = function () { // xmlHttp的任意一种状态变化都会调用本方法
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) { // 状态码200表示服务器已成功处理了请求
                        var text = xmlHttp.responseText;
                        var errorSpan = document.getElementById("errorSpan");
                        if (text == "1")
                            errorSpan.innerText = "用户名已被注册！";
                        else errorSpan.innerText = "恭喜你，该用户名可用！"
                    }
                }
            }
        }
    </script>
</head>
<body>
    <form action="" method="post">
        用户名:<input type="text" name="name" id="usernameEle" /><span id="errorSpan"></span><br/>
        密  码:<input type="password" name="password"/><br/>
    </form>
</body>
</html>
