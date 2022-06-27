<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Student Assessment page</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subject" value="${sessionScope.subject}"/>
<c:set var="level" value="${sessionScope.level}"/>
<c:set var="studentList" value="${sessionScope.studentList}" />
<c:set var="curriculumRecordList" value="${sessionScope.recordList}" />

<table border="1" width="700" align="center" cellspacing="5" cellpadding="20">

<caption><fmt:message key="msg.teacher.name"/> ${teacher.lastName}  ${teacher.firstName}  ${teacher.middleName}
<fmt:message key="msg.subject.name"/> ${subject.name}
<fmt:message key="msg.level.name"/> ${level.name} </caption>

    <tr>
        <th colspan="3"><fmt:message key="msg.student.name"/> </th>
        <c:forEach var="record" items="${curriculumRecordList}">
            <th>${record.lessonDate}</th>
        </c:forEach>
    </tr>

    <c:forEach var="student" items="${studentList}">
        <tr>
            <td colspan="3">
                    ${student.id} ${student.lastName} ${student.firstName}
            </td>
            <c:forEach var="record" items="${curriculumRecordList}">
                <td>
                    <form action="/addAssessmentRecord" method="post">
                        <label><input type="radio" name="recordId"  value="${record.id}" checked hidden></label>
                        <label><input type="radio" name="studentId" value="${student.id}" checked hidden></label>
                        <label><input type="text" name="grade" size="5" maxlength="5"></label>
                        <input type="submit" value="<fmt:message key="button.add"/> ">
                   </form>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>

</body>

</html>
