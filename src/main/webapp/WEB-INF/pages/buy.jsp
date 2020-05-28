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
            background-color: #ccffcc;
            text-align: center;
            color: black;
            font-family: 'Times New Roman', Times, serif;
            font-size: 18pt;
        }
        .layer > div {
            background-color: white;
            text-align: center;
            float: center;
            width: 30%;
            margin: 35px;
            padding: 200px;
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
<p align="center">${mess}</p>
<c:if test="${!(pageContext.request.userPrincipal.name == user.username)}">
    <c:if test="${!empty product}">
        <table align="center" color="white">
        <td class="dino">
            <img src=${product.pictureUrl} >
            </div>
        </td>
        <td>
            <p>${product.brand}</p>
            <p>${product.typeOfProduct}</p>
            <p>${product.size}</p>
            <p>${product.price}</p>
            <a href="<c:url value='/seller/${userService.getUserById(product.seller).id}'/>">${userService.getUserById(product.seller).name}</a>
            <p><c:if test="${product.sold == 0}">Не продано</c:if>
                <c:if test="${product.sold == 1}"><span style="color: red; ">Продано</span></c:if></p>
            <p>${product.condition}</p>
            <button><a href="<c:url value='/product/${product.id}'/>">Перейти</a></button>
        </td>
        <td>
        <c:if test="${!orderMade}">
            <h3>Пожалуйста, заполните форму заказа.</h3>
            <c:url var="addOrder" value="${product.id}"/>
            <form:form action="${addOrder}" commandName="orderForm">

                <table align="center">
                    <tr>
                        <td>
                            <form:label path="email">
                                <spring:message text="Введите вашу почту"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="email" required = "required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="street">
                                <spring:message text="Улица"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="street" required = "required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="house">
                                <spring:message text="Дом"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="house" required = "required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="apartment">
                                <spring:message text="Квартира"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="apartment" required = "required"/>
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
                                <form:option value="" label="--- Выберите город ---"/>
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
    </c:if>
    </td>
    </table>
</c:if>
</body>
</html>