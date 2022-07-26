<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>

<head>
    <title>Teacher schedule</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>
<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="teacherScheduleList" value="${sessionScope.teacherSchedule}"/>
<c:set var="daysOfWeek" value="${sessionScope.daysOfWeek}"/>

<table border="1" width="900" align="center" cellspacing="5" cellpadding="10">
    <caption> <fmt:message key="msg.teacher.name"/> ${teacher.lastName} ${teacher.firstName} ${teacher.middleName}</caption>
   <tr>
       <th>
           <fmt:message key="label.dayOfWeek"/>
       </th>
        <th>
              <fmt:message key="label.time"/>
       </th>
       <th>
           <fmt:message key="msg.level.name"/>
       </th>
       <th>
           <fmt:message key="msg.subject.name"/>
       </th>
    </tr>
    <c:forEach var="teacherShedule" items="${teacherScheduleList}">
        <tr>
            <td>
                ${teacherShedule.dayOfWeek}
            </td>
            <td>
                ${teacherShedule.time}
            </td>
            <td>
                ${teacherShedule.level.name}
            </td>
            <td>
                ${teacherShedule.subject.name}
            </td>
        </tr>
    </c:forEach>
</table>
<a href= <c:url value="teacher.jsp"/> > <fmt:message key="ref.return.teacherPage"/> </a>
</body>
</html>
