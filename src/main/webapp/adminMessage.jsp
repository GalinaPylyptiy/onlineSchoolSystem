<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<fmt:setBundle basename="locale"/>
<h1><fmt:message key="msg.successful.record.add"/> </h1>
<c:set var="scheduleRecord" value="${requestScope.record}"/>
<c:set  var="teacher" value="${requestScope.teacher}"/>

<c:set var="student" value="${requestScope.student}"/>

<h2> ${scheduleRecord.dayOfWeek} ${scheduleRecord.time}  ${scheduleRecord.level.name} ${scheduleRecord.subject.name}
    ${scheduleRecord.teacher.lastName} ${scheduleRecord.teacher.firstName}</h2>

<h2><fmt:message key="msg.teacher.name"/>${teacher.lastName} ${teacher.firstName} ${teacher.middleName}</h2>
<h2>${teacher.type.typeName}</h2>

<h2>${student.lastName} ${student.firstName} ${student.middleName}  ${student.level.name}</h2>

<a href="<c:url value="admin.jsp"/> "><fmt:message key="ref.return.main.page"/> </a>

</body>
</html>
