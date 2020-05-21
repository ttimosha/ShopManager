<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body onload='document.loginForm.username.focus();'>
<h1></h1>
<h2><a href="<c:url value="/products"/>" target="_blank">Главная</a></h2>
${message}
${error}

<form name='login' action="<c:url value="/login"/>" method='POST'>
    <table>
        <tr>
            <td>UserName:</td>
            <td><input type='text' name='username'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>

</form>
</body>
</html>