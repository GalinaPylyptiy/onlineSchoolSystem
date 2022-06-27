<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<h1 align="center"><i><fmt:message key="msg.greetings"/> </i> </h1>

<h2><fmt:message key="label.choose.role"/></h2>

<form action="teacherLogin.jsp" method="post">
    <input  type="submit" name="login" value="<fmt:message key="label.teacher.role"/> ">
</form>

<form action="studentLogin.jsp" method="post">
    <input  type="submit" name="login" value="<fmt:message key="label.student.role"/>">
</form>

</body>
</html>
