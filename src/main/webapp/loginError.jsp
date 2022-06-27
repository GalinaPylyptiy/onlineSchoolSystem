<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login error page</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@page isELIgnored="false" %>
</head>
<body>
<h1><c:out value="${requestScope.loginError}"/></h1>
<form action="index.jsp" method="post">
    <input type="submit" name="index" value="return to initial page" maxlength="50" >
</form>
</body>
</html>
