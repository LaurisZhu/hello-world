<%--
  Created by IntelliJ IDEA.
  User: Lawrence
  Date: 2017/9/2
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!-- head标签中包含的信息无需改动 -->
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
        nav {
            background-color: rgb(19,20,21);
            color: #999;
            position: fixed;
            top: 60px;
            left: 0;
            bottom: 0;
            padding-top: 25px;
        }
        nav li {
            list-style: none;
            padding: 15px 50px 15px 25px;
        }
        nav li:hover {
            cursor: pointer;
            background-color: rgba(50,51,52,0.5);
        }
        nav .active {
            background-color: rgba(90,91,92,0.5) !important;
        }
        .content {
            margin-left: 180px;
            padding-left: 2em;
        }
        .content h2 {
            padding: 20px 0;
        }
        input, select {
            display: block;
            border-radius: 5px;
            background-color: white;
            border: 1px solid #ccc;
            margin-bottom: 30px;
            height: 35px;
            font-size: 18px;
            text-indent: 1em;
            width: 350px;
        }
        button {
            display: block;
            width: 150px;
            text-align: center;
            height: 40px;
            line-height: 40px;
            color: white;
            background-color: rgb(54,133,250);
            font-size: 18px;
            border: 0;
            border-radius: 5px;
        }
        .content section {
            display: none;
        }
        .content .active {
            display: block;
        }
        table {
            background-color: #eee;
            margin: 50px;
            border: 1px solid #999;
            border-collapse: collapse;
        }
        th,td {
            border: 1px solid #ccc;
            padding: 10px 25px;
            min-width: 100px;
            text-align: center;
        }
        h5 {
            color: red;
        }
    </style>
</head>
<!-- 以上标签中包含的信息无需改动 -->

<body>

<!-- 顶部导航栏，无需改动 -->
<h1>服务器平台后台考核-信息管理</h1>

<table>
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <td>学号</td>
        <td>专业</td>
    </tr>
    <tr>
        <td>${}</td>
    </tr>
</table>
</body>
</html>
