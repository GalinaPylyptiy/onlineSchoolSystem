<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Initial page</title>
<link type="text/css" rel="stylesheet" href="<c:url value="css/indexPage.css"/> "/>
</head>
<body>
<c:import url="chooseLocale.jsp"/>
<c:set var="localeList" value="${sessionScope.localeList}"/>
<c:forEach var="locale" items="${localeList}">
    <p><a href="index.jsp?locale=${locale.shortName}">${locale.fullName}</a></p>
</c:forEach>

<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param ['locale']}" scope="session" />
    <fmt:setBundle basename="locale"/>
</c:if>

<form method="get" action="<c:url value='index.jsp' />">
    <input type=submit value="Stay in session" hidden>
</form>

<c:import url="welcome.jsp"/>

</body>
</html>
