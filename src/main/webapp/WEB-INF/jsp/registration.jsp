
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Quest Project - Регистрация</title>
    <style>
        <%@include file="/WEB-INF/css/my.css"%>
    </style>
</head>
<img id="logo" src="../../images/logo.png"/>

<body class="startPage">
<div class="divStartPage">
<form class="form-horizontal">
    <fieldset class="login">

        <!-- Form Name -->
        <legend class="legend">Регистрация</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="legend" for="name">Введите имя: </label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" class="input">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="legend" for="login">Введите логин: </label>
            <div class="col-md-4">
                <input id="login" name="login" type="text" class="input">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="legend" for="password">Введите пароль: </label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" class="input">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="register"></label>
            <div class="col-md-4">
                <button formmethod="post" id="register" name="register" class="btn btn-success">Зарегистрироваться</button>
            </div>
        </div>

    </fieldset>
</form>
</div>
</body>
</html>