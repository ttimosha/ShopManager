<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница пользователя</title>
    <style>
        body {
            background-color: #ccffcc;
            text-align: center;
            color: black;
            font-family: 'Times New Roman', Times, serif;
        }
        th, td {
            padding: 15px;
            text-align: center;
            font-size: 16pt;
            cellspacing: "0";
        }

    </style>
</head>
<body>
<h1>Личный кабинет</h1>
<h2><a href="<c:url value="/products"/>" target="_blank">Главная</a></h2>
<sec:authorize access="!isAuthenticated()">
    <h4 align="right"><button><a href="<c:url value="/login"/>">Войти</a></button></h4>
    <h4 align="right"><button><a href="<c:url value="/registration"/>">Регистрация</a></button></h4>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <h3 align="right">${pageContext.request.userPrincipal.name}</h3>
    <h3 align="right"><button><a href="<c:url value="/account/${pageContext.request.userPrincipal.name}"/>">Личный кабинет</a></button></h3>
    <h4 align="right"><a href="<c:url value="/logout"/>">Выйти</a></h4>
</sec:authorize>
<p><button><a href="<c:url value="/search"/>">Расширенный поиск</a> </button></p>
<c:if test="${pageContext.request.userPrincipal.name == user.username}">
<h2>${user.name}</h2>
<h3>Информация о пользователе:</h3>
<table class="tg" border="0"align="center">
    <tr>
        <td class="vl">Телефон:</td><td>${user.tele}</td>
    </tr>
    <tr>
        <td class="vl">Город:</td><td>${user.city}</td>
    </tr>
</table>
<h4><a href="<c:url value="/account/${user.username}/add/${user.id}"/>">Добавить товар</a></h4>
    <h4><a href="<c:url value="/account/${user.username}/orders/${user.id}"/>">Заказы на ваши товары</a></h4>
<c:if test="${!empty listProductsByUser}">
<h3>Ваши товары:</h3>
<table class="tg" border="0" bgcolor="#f8f8ff" align="center">
    <tr>
        <th width="240"></th>
        <th width="120">Бренд</th>
        <th width="120">Тип продукта</th>
        <th width="120">Цена</th>
        <th width="60">Размер</th>
        <th width="60">Продано</th>
        <th width="60">Состояние</th>
        <th width="60"></th>
        <th width="60"></th>
    </tr>
    <c:forEach items="${listProductsByUser}" var="product">
        <tr>
            <td><img src=${product.pictureUrl} width="100%" height="100%"></td>
            <td>${product.brand}</td>
            <td>${product.typeOfProduct}</td>
            <td>${product.price} ₽</td>
            <td>${product.size}</td>
            <td> <c:if test="${product.sold == 0}">Не продано</c:if>
                <c:if test="${product.sold == 1}">Продано</c:if>
            </td>
            <td>${product.condition}</td>
            <td><button><a href="<c:url value='/product/${product.id}'/>" target="_blank">Перейти</a></button></td>
            <td><a href="<c:url value="/account/${user.username}/edit/${product.id}"/>">Редактировать</a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
</c:if>
</body>
</html>
