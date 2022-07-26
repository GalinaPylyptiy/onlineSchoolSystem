<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Error page</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>
<h1><c:out value="${requestScope.error}"/></h1>
<a href="<c:url value="addCurriculum.jsp"/> "> <fmt:message key="ref.return"/></a>
</body>
</html>
