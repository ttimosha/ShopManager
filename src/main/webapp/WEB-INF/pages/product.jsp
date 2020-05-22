<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница продукта</title>
    <style>
        body {
            background-color: #ccffcc;
            text-align: center;
            color: black;
            font-family: 'Times New Roman', Times, serif;
            font-size: 18pt;
        }
        .layer > div {
            background-color: white;
            text-align: center;
            float: left;
            width: 45%;
            margin: 35px;
            padding: 10px;
            border: 1px ;

        }

    </style>
</head>
<body>
<h1>Интернет-площадка для продажи одежды</h1>
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
<c:if test="${!empty product}">
    <div class="layer">
        <div>
            <img src=${product.pictureUrl}>
        </div>
        <div>
            <p><a href="<c:url value='/seller/${user.id}'/>">${user.name}</a></p>
            <p>${user.city}</p>
            <p>${user.tele}</p>
            <p>Бренд: ${product.brand}</p>
            <p>${product.typeOfProduct}</p>
            <p>Размер: ${product.size}</p>
            <p>Состояние: ${product.condition}</p>
            <p>${product.description}</p>
            <p>Цена: ${product.price} рублей</p>
            <p><c:if test="${product.sold == 0}">Не продано</c:if>
                <c:if test="${product.sold == 1}"><span style="color: red; ">Продано</span></c:if></p>
            <c:if test="${!(pageContext.request.userPrincipal.name == user.username)}">
            <p><button><a href="<c:url value="/buy/${product.id}"/>" target="_blank">Купить</a></button></p>
            </c:if>
        </div>
    </div>
</c:if>
</body>
</html>
