<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Quest Project - User</title>
    <style>
        <%@include file="/WEB-INF/css/my.css"%>
    </style>
</head>
<body class="loginPage">
    <div style="display: flex;flex-direction: column; position: absolute;top: 0;right: 0;bottom: 0;left: 0;margin: auto;">
        <form>
            <button formmethod="post" id="exit" name="exit" class="btn btn-success" style="width: 75px; background-image: linear-gradient(rgba(252,5,35,0.74), #271739); margin-right: 1%">Выход</button>
            <table class="tableButtons">
                <tbody>
                <tr>
                    <td><button formmethod="post" name="newGame" class="btn btn-success" style="margin: 0 auto;">Новая игра</button></td>
                    <td><button formmethod="post" name="continueGame" class="btn btn-success" ${sessionScope.progress!=null?"":"disabled"} style="margin: 0 auto;">Продолжить игру</button></td>
                </tr>
                <tr>
                    <td><button formmethod="post" name="viewStatistics" class="btn btn-success" ${sessionScope.countGames!=null?"":"disabled"} style="margin: 0 auto;">Посмотреть статистику</button></td>
                    <td><button formmethod="post" name="editProfile" class="btn btn-success" style="margin: 0 auto;">Редактировать профиль</button></td>
                </tr>
                </tbody>
            </table>
        </form>
        <c:if test="${requestScope.user!=null}">
            <fieldset class="userPage" style="margin-top: 5%">
                <legend class="legend">Данные пользователя: ${requestScope.user.username}</legend>
                <form class="form-horizontal" method="post">
                    </br>
                    <label class="legend"style="display: block;float: left;width: 80px;height: 15px;">ID: </label><input readonly class="input" name="id" value="${sessionScope.id}">
                    </br>
                    <label class="legend" style="display: block;float: left;width: 80px;height: 15px;" for="name">Имя: </label>
                    <input id="name" name="name" type="text" value="${requestScope.user.username}" class="input">
                    </br>
                    <label class="legend" style="display: block;float: left;width: 80px;height: 15px;" for="login">Логин: </label>
                    <input id="login" name="login" type="text" value="${requestScope.user.login}" class="input">
                    </br>
                    <label class="legend" style="display: block;float: left;width: 80px;height: 15px;" for="password">Пароль: </label>
                    <input id="password" name="password" type="password" value="${requestScope.user.password}" class="input">
                    </br>
                    <button formmethod="post" id="save" name="saveChangeUser" value="save" class="btn btn-success">Сохранить</button>
                </form>
            </fieldset>
        </c:if>
        <c:if test="${requestScope.games!=null}">
            <strong class="legend" style="margin-top: 2%;margin-left: 2%;font-weight: 600;">Всего сыграно: ${requestScope.gameCount} игр. Из них: ${requestScope.loose} поражений и ${requestScope.winner} побед.</strong>
            <div style="display: inline-table; margin-top: 2%">
                <c:forEach var="game" items="${requestScope.games}">
                    <c:if test="${game.endGame==true}">
                    <table class="table">
                        <tbody>
                        <tr>
                            <th colspan="2">${game.status==true?"Победа":"Поражение"}</th>
                        </tr>
                        <tr>
                            <td>Дата начала:</td>
                            <td>${game.dateStart}</td>
                        </tr>
                        <tr>
                            <td>Дата окончания:</td>
                            <td>${game.dateFinish}</td>
                        </tr>
                        <tr>
                            <td>Прогресс:</td>
                            <td>Вы ответили на ${game.progress} вопросов из ${requestScope.countPages}.</td>
                        </tr>
                        </tbody>
                    </table>
                    </c:if>
                </c:forEach>
                </div>
        </c:if>
    </div>
</body>
</html>