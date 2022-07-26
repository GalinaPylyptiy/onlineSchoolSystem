<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Assessment list</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>

<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subject" value="${sessionScope.subject}"/>
<c:set var="level" value="${sessionScope.level}"/>
<c:set var="assessmentList" value="${sessionScope.assessmentList}"/>


<h2><fmt:message key="msg.teacher.name"/>${teacher.lastName} ${teacher.firstName} ${teacher.middleName}</h2>
<h2><fmt:message key="msg.subject.name"/> ${subject.name}</h2>
<h2><fmt:message key="msg.level.name"/> ${level.name}</h2>

<table border="1" width="700" align="center" cellspacing="5" cellpadding="20">
<tr>
    <th><fmt:message key="label.No"/> </th>
    <th colspan="2"><fmt:message key="msg.student.name"/></th>
    <th><fmt:message key="label.lessonDate"/></th>
    <th><fmt:message key="label.grade"/></th>
    <th><fmt:message key="label.lessonTheme"/></th>
    <th><fmt:message key="button.delete.record"/> </th>
</tr>
    <c:forEach var="assessment" items="${assessmentList}" >
    <tr>
        <td>${assessment.id}</td>
        <td colspan="2">${assessment.student.lastName} ${assessment.student.firstName}</td>
        <td>
      <fmt:parseDate var="lessonDate" value="${assessment.curriculumRecord.lessonDate}" pattern="yyyy-MM-dd" type="date" dateStyle="short"/>
       <fmt:formatDate var="formatedDate" value="${lessonDate}" pattern="dd.MM.yyyy" type="date" dateStyle="short"/>
            ${formatedDate}
        </td>
        <td>${assessment.grade}</td>
        <td>${assessment.curriculumRecord.lessonTheme}</td>
        <td>
            <a href="/deleteAssessmentRecord?assessmentRecordId=${assessment.id}">
                <fmt:message key="button.delete.record"/> <fmt:message key="label.No"/> ${assessment.id}"
            </a>
        </td>
    </tr>
    </c:forEach>
</table>

<a href="<c:url value="infoForAssessment.jsp"/> "><fmt:message key="ref.add.new.assessment"/> </a>
<br/>
<a href="<c:url value="teacher.jsp"/>"><fmt:message key="ref.return.main.page"/> </a>
</body>
</html>
