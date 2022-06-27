<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Student`s page</title>
    <style>
        body {
            background-image: url("img/studentBackground.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>

<c:set var="student" value="${sessionScope.student}"/>

<table border="1" width="1200" align="center" cellspacing="5" cellpadding="10">
    <caption><c:out value="${student.id}"/> <c:out value="${student.lastName}"/> <c:out value="${student.firstName}"/>
            <c:out value="${student.level.name}"/></caption>
    <tr align="center">
        <td colspan="3"> <fmt:message key="label.action.choose"/> </td>
    </tr>
    <tr align="center">
        <td> <form method="post" action="/getSubjectListAttribute">
            <input type=submit  value="<fmt:message key="button.get.grades"/> " >
        </form> </td>
        <td> <form method="post" action="/getAttributesForHomeTask">
            <input type=submit  value="<fmt:message key="button.watch.homeTask"/> "  >
        </form> </td>
        <td> <form method="post" action="/getAttributesForSchedule">
            <input type=submit  value="<fmt:message key="button.watch.schedule"/> "  >
        </form> </td>
    </tr>
</table>
<br/> <br/>
<a href="<c:url value="index.jsp"/> "> <fmt:message key="ref.exit"/> </a>
</body>
</html>
