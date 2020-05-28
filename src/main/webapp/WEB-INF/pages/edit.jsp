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
<h3>Редактировать товар</h3>
<p>${mess}</p>
    <form:form commandName="product" method="post">
        <table align="center">

            <tr>
                <td>
                    <form:label path="brand">
                        <spring:message text="Бренд"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="brand" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="typeOfProduct">
                        <spring:message text="Выберите тип продукта"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="typeOfProduct">
                        <form:option value="" label="--- Выберите ---" required="required"/>
                        <form:options items="${nameTypes}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="price">
                        <spring:message text="Введите цену"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="price" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="size">
                        <spring:message text="Размер"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="size">
                        <form:option value="NONE" label="--- Выберите ---"/>
                        <form:options items="${nameSizes}"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="condition">
                        <spring:message text="Состояние"/>
                    </form:label>
                </td>
                <td>
                    <form:select path="condition">
                        <form:option value="" label="--- Выберите ---"/>
                        <form:options items="${nameConditions}"/>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="description">
                        <spring:message text="*Кратко опишите причину продажи (до 100 символов)"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="description" maxlength="100" size="100"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="pictureUrl">
                        <spring:message text="*Добавьте ссылку на изображение"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="pictureUrl" maxlength="200" size="100"/>
                </td>
            </tr>
            <tr style="display:none;">
                <td >
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
                <td>
                    <form:input path="sold" readonly="true" disabled="true"/>
                    <form:hidden path="sold"/>
                </td>
                <td>
                    <form:input path="seller" readonly="true" disabled="true"/>
                    <form:hidden path="seller"/>
                </td>

            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="<spring:message text="Сохранить"/>"/>
                </td>
            </tr>
        </table>
    </form:form>
</c:if>
</body>
</html>
