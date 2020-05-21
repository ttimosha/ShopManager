<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Интернет-площадка для продажи одежды</title>
    <style>
        body {
            background-color: #ccccff;
            text-align: center;
            color: black;
            font-family: 'Times New Roman', Times, serif;
        }
        th, td {
            padding: 15px;
            text-align: center;
            font-size: 16pt;
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

<form:form method="GET" modelAttribute="productSearchCriteria">
    <fieldset>
        <table>
            <tr>
                <td><form:label path="brand"><spring:message text="Введите бренд"/></form:label></td>
                <td><form:input path="brand" /></td>
            </tr>
            <tr>
                <td><form:label path="typeOfProduct"><spring:message text="Выберите тип продукта" /></form:label></td>
                <td>
                    <form:select path="typeOfProduct">
                        <form:option label="--- Выберите ---" value=""/>
                        <form:options items="${nameTypes}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:label path="size"><spring:message text="Выберите размер" /></form:label></td>
                <td>
                    <form:select path="size">
                        <form:option value="" label="--- Выберите ---"/>
                        <form:options items="${nameSizes}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:label path="condition"><spring:message text="Выберите состояние" /></form:label></td>
                <td>
                    <form:select path="condition">
                        <form:option value="" label="--- Выберите ---"/>
                        <form:options items="${nameConditions}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:label path="priceMore"><spring:message text="Цена от "/></form:label></td>
                <td><form:input path="priceMore" /></td>
                <td><form:label path="priceLess"><spring:message text="Цена до "/></form:label></td>
                <td><form:input path="priceLess" /></td>
            </tr>
        </table>
    </fieldset>
    <button id="search">Искать</button>
</form:form>
${err}
<c:if test="${!empty listProducts}">
    <table class="tg" border="0" bgcolor="#f8f8ff" align="center">
        <tr>
            <th width="240"></th>
            <th width="120">Бренд</th>
            <th width="120">Тип продукта</th>
            <th width="120">Цена</th>
            <th width="60">Размер</th>
            <th width="60">Продавец</th>
            <th width="60">Продано</th>
            <th width="60">Состояние</th>
            <th width="60"></th>
        </tr>
        <c:forEach items="${listProducts}" var="product">
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
        </c:forEach>
    </table>
</c:if>
</body>
</html>
