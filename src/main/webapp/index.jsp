<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>Hello World!</h2>

<form action="/test" method="post">
    <label for="login_input">Login : </label>
    <input type="text" id="login_input" name="login">
    <br/>
    <label for="city_input">City  : </label>
    <select id="city_input" name="city">
        <c:forEach items="${requestScope.cities}" var="city">
            <option name="${city.name}" value="${city.id}">${city.name}</option>
        </c:forEach>
    </select>
    <br/>
    <input type="submit" value="Register">

</form>
</body>
</html>
