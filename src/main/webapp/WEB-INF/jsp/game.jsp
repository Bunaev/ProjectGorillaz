<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quest Project - Игра</title>
    <style>
        <%@include file="/WEB-INF/css/my.css"%>
    </style>
</head>
<body class="loginPage" style="background-image: url('../../images/intro.jpg'); background-size: 105%;">
    <form method="post">
        <button formmethod="post" id="exit" name="exit" class="btn btn-success" style="width: 75px; background-image: linear-gradient(rgba(252,5,35,0.74), #271739); margin-right: 1%">Выход</button>
    <c:if test="${requestScope.intro!=null}">
    <div class="legendIntro">
        <strong>${requestScope.intro}</strong>
    </div>
        <button formmethod="post" id="startGame" name="nextPage" value="1" class="btn btn-success" style="display:inline-block; margin-left: 20%">Начать поход</button>
    </c:if>
    <c:if test="${sessionScope.questions!=null}">
        <div class="legendIntro">
        <strong>${sessionScope.title}</strong>
        <c:forEach var="question" items="${sessionScope.questions}">
            <legend class="legendIntro"><input type="radio" name="radioButton" value="${question.statusQuestion}">${question.content}</legend>
        </c:forEach>
        </div>
        <button formmethod="post" id="startGame" name="nextPage" value="1" class="btn btn-success" style="display:inline-block; margin-left: 20%">Ответить</button>
    </c:if>
        <c:if test="${sessionScope.winner=='true'}">
            <img src="../../images/winner.png" style="position: absolute;height: 100%; float: right; top: 0;right: 0;" alt="">
            <legend class="legendIntro" style="font-size: 25px; margin-top: 4%; margin-left: 2%">Заказ выполнен! Награда твоя:</legend>
            <img src="../../images/gold.png" style="position: absolute;height: 30%;top: 20%;left: 20%; box-shadow: #00000a" alt="">
            <img src="../../images/medallion.png" style="position: absolute;height: 60%;top: -7%;left: 38%; opacity: 0.8" alt="">
            <div style="margin-top: 15%; margin-left: 20%; display: inline-block">
                <button formmethod="post" name="backToMainMenu" class="btn btn-success">Вернуться в главное меню</button>
                <button formmethod="post" name="exit" class="btn btn-success" style="width: 75px; background-image: linear-gradient(rgba(252,5,35,0.74), #271739)">Выход</button>
            </div>
        </c:if>
        <c:if test="${sessionScope.winner=='false'}">
            <img src="../../images/death.png" style="position: absolute;height: 100%; float: right; top: 0;right: 0;" alt="">
            <legend class="legendIntro" style="font-size: 25px; margin-top: 4%; margin-left: 2%">Заказ провален! Ты недостаточно подготовлен.</legend>
            <div style="margin-top: 15%; margin-left: 20%; display: inline-block">
                <button formmethod="post" name="backToMainMenu" class="btn btn-success">Вернуться в главное меню</button>
                <button formmethod="post" name="exit" class="btn btn-success" style="width: 75px; background-image: linear-gradient(rgba(252,5,35,0.74), #271739)">Выход</button>
            </div>
        </c:if>
</form>
</body>
</html>
