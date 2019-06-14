<%--
  Created by IntelliJ IDEA.
  User: MakaLoo
  Date: 2019/6/14
  Time: 16:17
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

        // 在文档加载完毕后发送请求得到所有省份名称，显示在<select name="province">
        // 选择新的省份时，发送请求，得到xml文档，即<province>元素
        // 解析xml文档，得到其中所有<city>，再得到每个<city>元素的内容，即市名，使用市名生成<option>，插入到<select>
        window.onload = function (ev) {
            var province = document.getElementById("p");
            var xmlHttp = createXMLHttpRequest();
            xmlHttp.open("GET", "/province.do", true);
            xmlHttp.send(null);
            xmlHttp.onreadystatechange = function () { // xmlHttp的任意一种状态变化都会调用本方法
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) { // 状态码200表示服务器已成功处理了请求
                    var text = xmlHttp.responseText;
                    var arr = text.split(",");
                    for (var i = 0; i < arr.length; i++) {
                        var op = document.createElement("option");
                        op.value = arr[i]; // value是该option选择后的标识符，相当于键值的键，textNode的值为值
                        var textNode = document.createTextNode(arr[i]);
                        op.appendChild(textNode);
                        province.appendChild(op);
                    }
                }
            };
            // 选择省份后显示对应的市
            province.onchange = function (ev) {
                var xmlHttp = createXMLHttpRequest();
                xmlHttp.open("POST", "/city.do", true);
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                var pname = province.value;
                pname = encodeURI(pname);
                pname = encodeURI(pname);
                xmlHttp.send("pname=" + pname);
                xmlHttp.onreadystatechange = function () { // xmlHttp的任意一种状态变化都会调用本方法
                    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) { // 状态码200表示服务器已成功处理了请求
                        // 把select的所有option移除，除了请选择这个
                        var citySelect = document.getElementById("c");
                        var optionList = citySelect.getElementsByTagName("option");
                        while (optionList.length > 1) {
                            citySelect.removeChild(optionList[1]);
                        }
                        var doc = xmlHttp.responseXML;
                        var cityList = doc.getElementsByTagName("city");
                        for (var i = 0; i < cityList.length; i++) {
                            var cityEle = cityList[i];
                            if (window.addEventListener) { // 其他浏览器的支持
                                var cityName = cityEle.textContent;
                            } else { // IE的支持
                                var cityName = cityEle.text;
                            }
                            var op = document.createElement("option");
                            op.value = cityName;
                            var textNode = document.createTextNode(cityName);
                            op.appendChild(textNode);
                            citySelect.appendChild(op);
                        }
                    }
                }
            };
        };
    </script>
</head>
<body>
    <select name="province" id="p">
        <option>===请选择省===</option>
    </select>

    <select name="city" id="c">
        <option>===请选择市===</option>
    </select>

    <h1 id="h1" >

    </h1>

</body>
</html>
