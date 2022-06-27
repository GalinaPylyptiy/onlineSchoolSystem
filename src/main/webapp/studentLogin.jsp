<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login as a student</title>
    <style>
        body {
            background-image: url("img/studentBackground.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<form  action="/studentLogin"  method="POST">
    <fieldset>
        <legend> Sign in </legend>
    <h2><label><strong><fmt:message key="msg.login"/> </strong></label></h2>
        <p><label><input type="text" name="login" size="30" maxlength="20"></label></p>
    <h2><label><strong><fmt:message key="msg.password"/> </strong></label></h2>
    <p><label><input type="password" name="password" size="30" maxlength="20" ></label></p>
    </fieldset>
    <h2> <input type="submit" name="submit" value="<fmt:message key="button.submit"/> "></h2>

</form>
</body>
</html>
