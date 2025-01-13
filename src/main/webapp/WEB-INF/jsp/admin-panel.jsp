<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Quest Project - Администратор</title>
    <style>
        <%@include file="/WEB-INF/css/my.css"%>
    </style>
</head>
<body class="loginPage">
<form class="form-horizontal">
    <button formmethod="post" id="exit" name="exit" class="btn btn-success" style="display:inline-block; float: right; width: 75px; background-image: linear-gradient(rgba(252,5,35,0.74), #271739);">Выход</button>
    <button formmethod="post" id="allUsers" name="allUsers" value="allUsers" class="btn btn-success" style="display:inline-block">Показать всех пользователей</button>
    <button formmethod="post" id="questEdit" name="questEdit" class="btn btn-success" style="display:inline-block">Отредактировать квест</button>
    </br>
    <c:if test="${sessionScope.users!=null}">
        <c:forEach var="user" items="${sessionScope.users}">
        <button formmethod="post" name="user" class="btn btn-success" style="display:inline-block; margin-top: 0;" value="${user.id}">${user.id} - ${user.username}</button>
    </c:forEach>
    </c:if>
<c:if test="${sessionScope.user!=null}">
    <c:if test="${sessionScope.id==sessionScope.user.id}">
        <fieldset class="userPage" style="margin-left: 15%">
            <form class="form-horizontal" method="post">
                <legend class="legend">Данные пользователя: ${sessionScope.user.username}</legend>
                </br>
                <label class="legend"style="display: block;float: left;width: 80px;height: 15px;">ID: </label><input readonly class="input" name="id" value="${sessionScope.user.id}">
                </br>
                <label class="legend" style="display: block;float: left;width: 80px;height: 15px;" for="name">Имя: </label>
                <input
                        id="name"
                        name="name"
                        type="text"
                        value="${sessionScope.user.username}"
                        class="input"
                        required="">
                </br>
                <label class="legend" style="display: block;float: left;width: 80px;height: 15px;" for="login">Логин: </label>
                <input
                        id="login"
                        name="login"
                        type="text"
                        value="${sessionScope.user.login}"
                        class="input"
                        required="">
                </br>
                <label class="legend" style="display: block;float: left;width: 80px;height: 15px;" for="password">Пароль: </label>
                <input
                        id="password"
                        name="password"
                        type="password"
                        value="${sessionScope.user.password}"
                        class="input"
                        required="">
                </br>
                <label class="legend" for="role" style="display: block;float: left;width: 80px;height: 15px;">Роль: </label>
                <select id="role" name="role" class="form-control">
                    <c:forEach var="role" items="${applicationScope.roles}">
                        <option value="${role}" ${role==requestScope.user.role?"selected":""}>${role}</option>
                    </c:forEach>
                </select>
                </br>
                <button formmethod="post" id="save" name="saveChangeUser" value="save" class="btn btn-success">Сохранить</button>
                </form>
            </fieldset>
        </c:if>
    <c:if test="${sessionScope.id!=sessionScope.user.id}">
        <fieldset class="userPage" style="margin-left: 15%">
            <form class="form-horizontal" method="post">
                <legend class="legend">Данные пользователя: ${sessionScope.user.username}</legend>
                </br>
                <label class="legend" style="display: block;float: left;width: 80px;height: 15px;">ID: </label><input class="input" readonly name="id" value="${sessionScope.user.id}">
                </br>
                <label class="legend" style="display: block;float: left;width: 80px;height: 15px;">Имя: </label><input class="input" readonly name="name" value="${sessionScope.user.username}">
                </br>
                <label class="legend" style="display: block;float: left;width: 80px;height: 15px;">Логин: </label><input class="input" readonly name="login" value="${sessionScope.user.login}">
                </br>
                <label class="legend" style="display: block;float: left;width: 80px;height: 15px;">Пароль: </label><input class="input" style="font-style: italic" readonly name="HiddenPassword" value="Скрыто">
                </br>
                <label class="legend" for="role" style="display: block;float: left;width: 80px;height: 15px;">Роль: </label>
                <select id="roleChange" name="role">
                    <c:forEach var="role" items="${applicationScope.roles}">
                        <option value="${role}" ${role==sessionScope.user.role?"selected":""}>${role}</option>
                    </c:forEach>
                </select>
                </br>
                <button formmethod="post" id="saveChangeUser" name="saveChangeUser" value="save" class="btn btn-success">Сохранить</button>
            </form>
        </fieldset>
    </c:if>
</c:if>

    <c:if test="${sessionScope.quest!=null}">
        <c:forEach var="entry" items="${sessionScope.quest}">
            <button formmethod="post" name="page" class="btn btn-success" style="margin-top: 0;display:inline-block" value="${entry.key}">Cтраница №${entry.key}</button>
        </c:forEach>
        <button formmethod="post" name="addPage" class="btn btn-success" style="margin-top: 0;display:inline-block">Добавить страницу</button>
    </c:if>
    <c:if test="${sessionScope.pageContent!=null}">
        </br>
        <label for="textAreaTitle" class="legend">Заголовок: </label>
        <textarea class="textAreaTitle" id="textAreaTitle" name="textAreaTitle">${sessionScope.title}</textarea>
        <c:forEach var="question" items="${sessionScope.pageContent}">
            </br>
            <label for="textAreaContent" class="legend">Вопрос №${sessionScope.pageContent.indexOf(question)+1}: </label>
            <textarea class="textAreaContent" id="textAreaContent" name="textAreaContent">${question.content}</textarea>
            <label for="selectQuestion" class="legend">Статус вопроса: </label>
            </br>
            <select name="statusQuestion" class="selectQuestion" id="selectQuestion">
                <option value="false" ${'false'==question.statusQuestion?"selected":""}>Неправильный</option>
                <option value="true" ${'true'==question.statusQuestion?"selected":""}>Правильный</option>
            </select>
            </br>
            <button formmethod="post" name="deleteQuestion" class="btn btn-success" style="margin-top: 1%;display:inline-block" value="${sessionScope.pageContent.indexOf(question)+1}">Удалить вопрос</button>
        </c:forEach>
        </br>
        <button formmethod="post" name="savePage" class="btn btn-success" style="display:inline-block">Сохранить страницу</button>
        <button formmethod="post" name="addQuestion" class="btn btn-success" style="display:inline-block" value="${sessionScope.page}">Добавить вопрос</button>
        <button formmethod="post" name="deletePage" class="btn btn-success" style="margin-top: 1%;display:inline-block" value="${sessionScope.page}">Удалить страницу</button>
    </c:if>
</form>
</body>
</html>