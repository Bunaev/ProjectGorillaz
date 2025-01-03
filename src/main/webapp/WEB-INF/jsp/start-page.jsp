<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Quest Project</title>
  <style>
    <%@include file="/WEB-INF/css/my.css"%>
  </style>
</head>
<img id="logo" src="../../images/logo.png"/>
<body class="startPage">
<div class="divStartPage">
<fieldset class="login">
  <legend class="legend" align="center">Вход/Регистрация</legend>
<form class="form-horizontal" action="login">
  <button id="enter" name="enter" value="enter" class="btn btn-success" style="margin: auto; display: block">Войти</button>
</form>
  <br/>
<form class="form-horizontal" action="registration">
  <button id="registration" name="registration" class="btn btn-success" style="margin: auto; display: block">Зарегистрироваться</button>
</form>
</fieldset>
</div>
</body>
</html>