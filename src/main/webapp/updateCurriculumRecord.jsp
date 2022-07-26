<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Update curriculum record</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>

<body>
<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subejct" value="${sessionScope.subejct}"/>
<c:set var="level" value="${sessionScope.level}"/>

<form action="/updateCurriculumRecord" method="POST">

    <table border="1" width="1200" align="center" cellspacing="6" cellpadding="20">
        <caption>
            <h1 align="center">${teacher.lastName} ${teacher.firstName} ${teacher.middleName}  </h1>
            <h2 align="center"><fmt:message key="msg.subject.name"/>  ${subject.name}</h2>
            <h3 align="center"><fmt:message key="msg.level.name"/>  ${level.name} </h3>
        </caption>
        <tr>
            <th>
                <label><strong><fmt:message key="msg.enter.record.No"/> </strong></label>
            </th>
            <th>
                <label><strong><fmt:message key="msg.lesson.date"/></strong></label>
            </th>
            <th>
                <label><strong><fmt:message key="msg.enter.lessonTheme"/> </strong></label>
            </th>
            <th>
                <label><strong><fmt:message key="msg.enter.homeTask"/></strong></label>
            </th>
        </tr>
        <tr align="center">
            <td><label><input type="radio" name="recordId" value="${param ['recordId']}" checked>${param ['recordId']}</label></td>
            <td><label><input type="date" name="lessonDate"></label></td>
            <td><label><input type="text" name="lessonTheme"></label></td>
            <td><label><input type="text" name="homeTask"></label></td>
        </tr>
    </table>
    <p> <input type="submit" name="submit" value="<fmt:message key="label.editRecord"/>"></p>
</form>

</body>
</html>
