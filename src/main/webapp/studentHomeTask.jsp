<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>watch home task</title>

</head>
<body>

<c:set var="student" value="${sessionScope.student}"/>
<c:set var="level" value="${sessionScope.level}"/>
<c:set var="subjectList" value="${sessionScope.subjectList}"/>

<form action="/watchHomeTask" method="GET">
    <table border="1" width="1200" align="center" cellspacing="6" cellpadding="20">
        <tr>
            <th colspan="2"><label><strong><fmt:message key="msg.student.name"/> </strong></label> </th>
            <th><label><strong><fmt:message key="msg.choose.subject"/> </strong></label> </th>
            <th><label><strong><fmt:message key="msg.level.name"/> </strong></label></th>
        </tr>
        <tr align="center">
            <td colspan="2"> ${student.id}  ${student.lastName} ${student.firstName} </td>
            <td><label><select name="subject">
                <c:forEach var="subjectName" items="${subjectList}">
                    <option>${subjectName.name}</option>
                </c:forEach></select></label> </td>
            <td><c:out value="${level.name}"/></td>
        </tr>
    </table>
    <p> <input type="submit" name="submit" value="<fmt:message key="button.watch.homeTask"/> "></p>
</form>
</body>
</html>
