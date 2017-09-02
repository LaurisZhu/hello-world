<%--
  Created by IntelliJ IDEA.
  User: Lawrence
  Date: 2017/9/2
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>%
<html>
<head>
    <meta charset="UTF-8">
    <title>服务器平台后台考核</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        h1 {
            height: 59px;
            line-height: 59px;
            border-bottom: 1px solid #ccc;
            box-shadow: 0 0 5px 3px #eee;
            background-color: #eee;
            color: #999;
            text-indent: 1.5em;
        }
        h5 {
            color: red;
        }
    </style>
</head>

<body>
<!-- 顶部导航栏，无需改动 -->
<h1>服务器平台后台考核-信息管理</h1>

<!-- 左侧导航栏，需要在合适的li标签上添加上 class="active" 字段，四个中只能选一个添加 -->

<h5>${requestScope.get("JSESSIONID")}</h5>
</body>
</html>
