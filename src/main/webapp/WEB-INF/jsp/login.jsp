<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quest Project - Вход</title>
    <style>
        <%@include file="/WEB-INF/css/my.css"%>
    </style>
</head>
<img id="logo" src="../../images/logo.png"/>
<body class="startPage">
<div class="divStartPage">
<br/>
<form class="form-horizontal">
    <fieldset class="login">

        <!-- Form Name -->
        <legend class="legend">Вход</legend>
        <div class="form-group">
            <label class="legend" for="login">Логин: </label>
            <div class="col-md-4">
                <input id="login" name="login" type="text" class="input">
            </div>
        </div>
        <!-- Password input-->
        <div class="form-group">
            <label class="legend" for="password">Пароль: </label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" class="input">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-8">
                <button formmethod="post" id="enter" name="enter" value="enter" class="btn btn-success">Войти</button>
            </div>
            <c:if test="${sessionScope.authorized==false}">
            <label class="error">Неверный логин или пароль!</label>
                <a href="registration" style="font-size: 20px;" class="error">Зарегистрироваться</a>
            </c:if>
            <c:if test="${sessionScope.banned==true}">
                <label class="error" style="font-size: 15px;">АККАУНТ ЗАБЛОКИРОВАН!</label>
            </c:if>
        </div>
    </fieldset>
</form>
</div>
</body>
</html>
