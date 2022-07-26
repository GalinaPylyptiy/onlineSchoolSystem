<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Student schedule</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>

<c:set var="student" value="${sessionScope.student}"/>
<c:set var="scheduleList" value="${sessionScope.studentScheduleList}"/>

<table border="1" width="1200" align="center" cellspacing="5" cellpadding="10">

<caption> <fmt:message key="msg.student.name"/>  ${student.id}  ${student.lastName} ${student.firstName}
    <fmt:message key="msg.level.name"/> ${student.level.name}
</caption>
    <tr>
        <th>
            <fmt:message key="label.dayOfWeek"/>
        </th>
        <th>
            <fmt:message key="label.time"/>
        </th>
        <th>
            <fmt:message key="msg.subject.name"/>
        </th>
    </tr>
    <c:forEach var="studentShedule" items="${scheduleList}">
        <tr>
            <td>
                    ${studentShedule.dayOfWeek}
            </td>
            <td>
                    ${studentShedule.time}
            </td>

            <td>
                    ${studentShedule.subject.name}
            </td>
        </tr>
    </c:forEach>
</table>
<a href= <c:url value="student.jsp"/> > <fmt:message key="ref.return.main.page"/> </a>
</body>
</html>
