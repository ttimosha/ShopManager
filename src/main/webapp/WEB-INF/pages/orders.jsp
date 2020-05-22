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
            font-size: 18pt;
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
<c:if test="${pageContext.request.userPrincipal.name == username}">
<h3>Ваши заказы:</h3>
    <c:if test="${!empty orderList}">
        <table class="tg" border="0" bgcolor="#f8f8ff" align="center">
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${order.city}, ${order.street}, ${order.house}, ${order.apartment}</td>
                    <td>${order.email}</td>
                    <td> <c:if test="${order.paid == 0}">Не оплачено</c:if>
                        <c:if test="${order.paid == 1}">Оплачено</c:if>
                    </td>
                    <td><button><a href="<c:url value='/product/${order.productId}'/>" target="_blank">Перейти к товару</a></button></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:if>
</body>
</html>
