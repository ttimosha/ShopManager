<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
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
<p align="right"><button><a href="login.html">Войти</a></button> <button><a href="reg.html">Регистрация</a></button></p>
<p><input name="q" type="search" placeholder="Поиск по сайту" /> <input type="submit" value="Найти" /></p>
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
                <td>${product.price}</td>
                <td>${product.size}</td>
                <td>${userService.getUserById(product.seller).name}</td>
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
