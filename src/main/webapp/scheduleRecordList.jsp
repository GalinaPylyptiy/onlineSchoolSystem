<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Schedule records</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>
<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subject" value="${sessionScope.subject}"/>
<c:set var="level" value="${sessionScope.level}"/>
<c:set var="record" value="${sessionScope.record}"/>
<c:set var="scheduleRecordList" value="${sessionScope.scheduleRecordList}"/>

<h1><fmt:message key="msg.successful.record.add"/> </h1>

<h2> ${record.dayOfWeek} ${record.time}  ${record.level.name} ${record.subject.name}
    ${record.teacher.lastName} ${record.teacher.firstName}</h2>

<table border="1" width="900" align="center" cellspacing="5" cellpadding="10">

    <caption> <fmt:message key="msg.teacher.name"/> ${teacher.lastName} ${teacher.firstName} ${teacher.middleName}</caption>
    <tr>
        <th>
            <fmt:message key="label.No"/>
        </th>
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
        <th>
            <fmt:message key="button.delete.record"/>
        </th>

    </tr>
    <c:forEach var="scheduleRecord" items="${scheduleRecordList}">
        <tr>
            <td>
                    ${scheduleRecord.id}
            </td>
            <td>
                    ${scheduleRecord.dayOfWeek}
            </td>
            <td>
                    ${scheduleRecord.time}
            </td>
            <td>
                    ${scheduleRecord.level.name}
            </td>
            <td>
                    ${scheduleRecord.subject.name}
            </td>
            <td>
                <a href="/deleteScheduleRecord?scheduleRecordId=${scheduleRecord.id}">
                <fmt:message key="button.delete.record"/> <fmt:message key="label.No"/> ${scheduleRecord.id}"
            </a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value="admin.jsp"/> "> <fmt:message key="ref.return.main.page"/> </a>
<br/> <br/>
<a href="<c:url value="schedule.jsp"/> "> <fmt:message key="button.add.schedule"/> </a>
</body>
</html>
