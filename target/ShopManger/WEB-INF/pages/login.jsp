<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        th, td {
            padding: 15px;
            text-align: center;
            font-size: 18pt;
            cellspacing: "0";}

    </style>
</head>
<body onload='document.loginForm.username.focus();'>
<h1></h1>
<h2><a href="<c:url value="/products"/>" target="_blank">Главная</a></h2>
${message}
${error}

<form name='login' action="<c:url value="/login"/>" method='POST'>
    <table align='center'>
        <tr>
            <td>Логин:</td>
            <td><input type='text' name='username' required='required'></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input type='password' name='password' required='required'/></td>
        </tr>
        <tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <td colspan='2'><input name="submit" type="submit" value="Войти" /></td>
        </tr>
    </table>
</form>
</body>
</html>