<%--
  Created by IntelliJ IDEA.
  User: MakaLoo
  Date: 2019/6/14
  Time: 12:33
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
            var btn = document.getElementById("btn");
            btn.onclick = function (ev1) {
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("GET", "/xml.do", true);
                xmlHttp.send(null);
                xmlHttp.onreadystatechange = function () { // xmlHttp的任意一种状态变化都会调用本方法
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) { // 状态码200表示服务器已成功处理了请求
                        var doc = xmlHttp.responseXML; // 这次获取的是responseXML，是一个document对象
                        var ele = doc.getElementsByTagName("student")[0];
                        var number = ele.getAttribute("number");
                        var name, age, gender;
                        if (window.addEventListener) { // 其他浏览器的支持
                            name = ele.getElementsByTagName("name")[0].textContent;
                            age = ele.getElementsByTagName("age")[0].textContent;
                            gender = ele.getElementsByTagName("gender")[0].textContent;
                        } else { // IE的支持
                            name = ele.getElementsByTagName("name")[0].text;
                            age = ele.getElementsByTagName("age")[0].text;
                            gender = ele.getElementsByTagName("gender")[0].text;
                        }
                        var text = number + ", " +  name + ", " + age + ", " + gender;
                        var h1 = document.getElementById("h1");
                        h1.innerText = text;
                    }
                }
            }
        }
    </script>
</head>
<body>
<button id="btn">点击这里</button>
<h1 id="h1"></h1>
</body>
</html>
