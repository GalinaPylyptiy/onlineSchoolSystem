<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Curriculum records</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>

<body>

<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subejct" value="${sessionScope.subejct}"/>
<c:set var="level" value="${sessionScope.level}"/>
<c:set var="recordList" value="${sessionScope.recordList}"/>

<table border="1" width="1200" align="center" cellspacing="6" cellpadding="20">
    <tr>
        <td colspan="3">${teacher.lastName} ${teacher.firstName} ${teacher.middleName}  </td>
        <td colspan="2">${subject.name}</td>
        <td>${level.name}</td>
    </tr>
    <tr>
        <th><fmt:message key="label.No"/></th>
        <th><fmt:message key="label.lessonDate"/></th>
        <th><fmt:message key="label.lessonTheme"/></th>
        <th><fmt:message key="label.homeTask"/></th>
        <th><fmt:message key="label.editRecord"/></th>
        <th><fmt:message key="button.delete.record"/> </th>
    </tr>
    <c:forEach var="record" items="${recordList}">

    <tr>
            <td>${record.id}</td>
            <td>
                <fmt:parseDate var="lessonDate" value="${record.lessonDate}" pattern="yyyy-MM-dd" type="date" dateStyle="short"/>
                <fmt:formatDate var="formatedDate" value="${lessonDate}" pattern="dd.MM.yyyy" type="date" dateStyle="short"/>
                    ${formatedDate}
<%--                    ${record.lessonDate}--%>
            </td>
            <td>${record.lessonTheme}</td>
            <td>${record.homeTask}</td>
            <td><a href="<c:url value="updateCurriculumRecord.jsp"/>?recordId=${record.id}">
                <fmt:message key="label.editRecord"/><fmt:message key="label.No"/> ${record.id} </a>
            </td>
        <td>
            <a href="/deleteCurriculumRecord?recordId=${record.id}">
                <fmt:message key="button.delete.record"/> <fmt:message key="label.No"/> ${record.id}"
            </a>
         </td>
    </tr>
    </c:forEach>
</table>
<p><a href="<c:url value="teacher.jsp"/> "><fmt:message key="ref.return.main.page"/> </a></p>
<p><a href="<c:url value="addCurriculum.jsp"/> "><fmt:message key="button.add.record"/></a></p>

</body>
</html>
