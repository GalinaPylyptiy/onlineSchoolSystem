<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My home task</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@page isELIgnored="false" %>
</head>
<body>
<fmt:setBundle basename="locale"/>
<c:set var="student" value="${sessionScope.student}"/>
<c:set var="level" value="${sessionScope.level}"/>
<c:set var="subject" value="${sessionScope.subject}"/>
<c:set var="recordList"  value="${sessionScope.recordList}"/>

<table border="1" width="700" align="center" cellspacing="5" cellpadding="5">

    <caption align="center"><h3>${student.lastName} ${student.firstName}</h3></caption>
    <tr><td colspan="7" align="center" >${subject.name}</td></tr>
    <tr>
        <th><fmt:message key="label.lessonDate"/></th>
        <th><fmt:message key="label.lessonTheme"/></th>
        <th><fmt:message key="label.homeTask"/></th>
    </tr>
    <c:forEach var="record" items="${recordList}">
    <tr>
            <td>${record.lessonDate}</td>
            <td>${record.lessonTheme}</td>
            <td>${record.homeTask}</td>

    </tr>
    </c:forEach>
</table>
<a href="<c:url value="student.jsp"/> "><fmt:message key="ref.return.main.page"/> </a>
</body>
</html>
