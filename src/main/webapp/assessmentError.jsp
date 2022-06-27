<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assessment error</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@page isELIgnored="false" %>
</head>
<body>
<h1><c:out value="${requestScope.assessmentError}"/></h1>
<a href="<c:url value="addStudentAssessment.jsp"/> "> go to previous page</a>
</body>
</html>
