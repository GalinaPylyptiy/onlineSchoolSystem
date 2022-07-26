<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Curriculum record</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>

</head>
<body>

<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subjectList" value="${sessionScope.subjectList}" />
<c:set var="levelList" value="${sessionScope.levelList}" />

<form action="/addCurriculumRecord" method="POST">

<table border="1" width="1200" align="center" cellspacing="6" cellpadding="20">
    <tr>
        <th><label><strong><fmt:message key="msg.lesson.date"/> </strong></label> </th>
        <th><label><strong><fmt:message key="msg.choose.subject"/></strong></label> </th>
        <th><label><strong><fmt:message key="msg.choose.level"/></strong></label></th>
        <th><label><strong><fmt:message key="msg.enter.lessonTheme"/></strong></label></th>
        <th><label><strong><fmt:message key="msg.enter.homeTask"/> </strong></label></th>
    </tr>
    <tr align="center">
        <label> <select name="teacherId" hidden>
        <option>${teacher.id}</option>
     </select></label>

        <td><label><input type="date" name="lessonDate"></label></td>
        <td>
            <label><select name="subject">
            <c:forEach var="subject" items="${subjectList}">
            <option>${subject.name}</option>
            </c:forEach>
            </select></label>
        </td>
        <td>
            <label><select name="level">
            <c:forEach var="level" items="${levelList}">
                <option>${level.name}</option>
            </c:forEach>
            </select></label>
        </td>
        <td>
            <label><input type="text" name="lessonTheme"></label>
        </td>
        <td>
            <label><input type="text" name="homeTask"></label>
        </td>
    </tr>
</table>
    <p> <input type="submit" name="submit" value="<fmt:message key="button.add.record"/>"></p>
</form>
<form action="teacher.jsp" method="get">
    <input type="submit" value="<fmt:message key="button.cancel"/>">
</form>
</body>
</html>
