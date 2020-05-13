<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница продукта</title>
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
            font-size: 18pt;
        }
    </style>
</head>
<body>
<h1>Интернет-площадка для продажи одежды</h1>
<p align="right"><button><a href="login.html">Войти</a></button> <button><a href="reg.html">Регистрация</a></button></p>
<p><input name="q" type="search" placeholder="Поиск по сайту" /> <input type="submit" value="Найти" /></p>
<c:if test="${!empty product}">
    <table class="tg" border="0" bgcolor="#f8f8ff" align="center">
        <tr>
            <td><img src="https://www.next.com.ru/nxtcms/resource/image/2751822/portrait_ratio1x1/525/525/2110828c49f32e2fc38cef3b0f58442/LP/shirts.jpg"></td>
            <td>${userService.getUserById(product.seller).name}</td>
        </tr>
        <tr>
            <td></td>
            <td>${userService.getUserById(product.seller).city}</td>
        </tr>
        <tr>
            <td></td>
            <td>${userService.getUserById(product.seller).tele}</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>${product.brand}</td>
        </tr>
        <tr>
            <td></td>
            <td>${product.typeOfProduct}</td>
        </tr>
        <tr>
            <td></td>
            <td>${product.size}</td>
        </tr>
        <tr>
            <td></td>
            <td>${product.condition}</td>
        </tr>
        <tr>
            <td></td>
            <td>${product.description}</td>
        </tr>
        <tr>
            <td></td>
            <td>${product.price}</td>
        </tr>
        <tr>
            <td></td>
            <td><c:if test="${product.sold == 0}">Не продано</c:if>
                <c:if test="${product.sold == 1}"><span style="color: red; ">Продано</span></c:if>
            </td>
        </tr>
                <td>${product.condition}</td>
                <td><button><a href="/buy/${product.id}" target="_blank">Купить</a></button></td>
            </tr>
    </table>
</c:if>

</body>
</html>
