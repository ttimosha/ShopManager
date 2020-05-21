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

<p><button><a href="<c:url value="/search"/>">Расширенный поиск</a> </button></p>
<h5>Данный сайт создан для покупки вещей.</h5>
</body>
</html>