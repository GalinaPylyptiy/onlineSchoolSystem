<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<style>
    body {
        background-image: url('img/backgroundTeacher.jpg');
        background-repeat: no-repeat;
        background-size: 100% 100%;
        background-attachment: fixed;
    }
</style>
<head>
    <title>Login as a teacher</title>
</head>
<body>

<form  action="/teacherLogin" method="POST">
    <h2><label><strong><fmt:message key="msg.login"/> </strong></label></h2>
       <p><label><input type="text" name="login" size="30" maxlength="20"></label></p>
    <h2><label><strong><fmt:message key="msg.password"/> </strong></label></h2>
        <p><label> <input type="password" name="password" size="30" maxlength="20" ></label></p>

    <h2> <input type="submit" name="submit" value="<fmt:message key="button.submit"/> "></h2>

</form>
</body>
</html>
