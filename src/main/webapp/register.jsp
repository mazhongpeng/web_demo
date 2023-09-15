<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/9/29
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="jQuery.js"></script>
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="dist/js/bootstrap.min.js"></script>
    <link href="css/register.css" rel="stylesheet" type="text/css" charset="UTF-8">
</head>
<body>
<form action="/studentLiving_demo_war/login?method=login"   method="post" class="informations">
    <div>
        <h3>用户登录</h3>
        账号：<input type="text"  required placeholder="请输入用户名" name="username"><br>
        <p>${usernameError}</p>
        密码：<input type="password" required placeholder="请输入密码" name="password">
        <p class="distance">${passwordError}</p>
        <div>
            <input type="radio" name="type" value="systemAdmin" class="radio-inline" checked>系统管理员
            <input type="radio" name="type" value="dormitoryAdmin" class="radio-inline">宿舍管理员
        </div>
        <input type="submit" value="登录">
    </div>
</form>
</body>
</html>