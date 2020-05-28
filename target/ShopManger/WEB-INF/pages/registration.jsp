<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
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
<h2><a href="<c:url value="/products"/>" target="_blank">Главная</a></h2>
<div>
    ${error}
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="name" placeholder="Имя" required="required"></form:input>
        </div>
        <div>
            <form:input type="text" path="tele" placeholder="Телефон" required="required"></form:input>
        </div>
        <div>
                <form:select path="city">
                    <form:option value="" label="--- Выберите город ---"/>
                    <form:options items="${listCities}"/>
                </form:select>
        </div>
        <div>
            <form:input type="text" path="username" placeholder="Имя пользователя"
                        autofocus="true" required="required"></form:input>
            <form:errors path="username"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Пароль" required="required"></form:input>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Подвердите пароль" required="required"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
</div>
</body>
</html>