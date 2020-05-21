<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Купить</title>
    <style>
        body {
            background-color: #ccccff;
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
    <h4 align="right"><a href="<c:url value="/logout"/>">Выйти</a></h4>
</sec:authorize>
<p><button><a href="<c:url value="/search"/>">Расширенный поиск</a> </button></p>
<p align="center">${errormess}</p>
<c:if test="${!empty product}">
    <div class="layer">
        <div>
            <table class="tg" border="0" bgcolor="#f8f8ff" align="center">
                    <tr>
                        <td><img src="https://www.next.com.ru/nxtcms/resource/image/2751822/portrait_ratio1x1/525/525/2110828c49f32e2fc38cef3b0f58442/LP/shirts.jpg" width="100%" height="100%"></td>
                        <td>${product.brand}</td>
                        <td>${product.typeOfProduct}</td>
                        <td>${product.price} ₽</td>
                        <td>${product.size}</td>
                        <td><a href="<c:url value='/seller/${userService.getUserById(product.seller).id}'/>">${userService.getUserById(product.seller).name}</a></td>
                        <td> <c:if test="${product.sold == 0}">Не продано</c:if>
                            <c:if test="${product.sold == 1}">Продано</c:if>
                        </td>
                        <td>${product.condition}</td>
                        <td><button><a href="<c:url value='/product/${product.id}'/>">Перейти</a></button></td>
                    </tr>
            </table>
            <c:if test="${!orderMade}">
            <h3>Пожалуйста, заполните форму заказа.</h3>
            <c:url var="addOrder" value="${product.id}"/>
            <form:form action="${addOrder}" commandName="orderForm">
                <table>
                    <tr>
                        <td>
                            <form:label path="email">
                                <spring:message text="Введите вашу почту"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="email"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="street">
                                <spring:message text="Улица"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="street"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="house">
                                <spring:message text="Дом"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="house"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="apartment">
                                <spring:message text="Квартира"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="apartment"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="city">
                                <spring:message text="Город"/>
                            </form:label>
                        </td>
                        <td>
                            <form:select path="city">
                                <form:option value="NONE" label="--- Выберите город ---"/>
                                <form:options items="${listCities}"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="<spring:message text="Подтвердить"/>"/>
                        </td>
                    </tr>
                </table>
            </form:form>
            </c:if>
                ${mess}
        </div>
    </div>
</c:if>
</body>
</html>
