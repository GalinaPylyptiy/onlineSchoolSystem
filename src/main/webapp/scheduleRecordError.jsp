<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Schedule record error</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>
<h1><c:out value="${requestScope.scheduleRecordError}"/></h1>
<a href="<c:url value="schedule.jsp"/> "> go to previous page</a>
</body>
</html>
