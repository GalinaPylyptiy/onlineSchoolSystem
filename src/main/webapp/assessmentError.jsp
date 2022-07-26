<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Assessment error</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>
<h1><c:out value="${requestScope.assessmentError}"/></h1>
<a href="<c:url value="addStudentAssessment.jsp"/> "> <fmt:message key="ref.return"/></a>
</body>
</html>
