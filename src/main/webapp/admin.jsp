<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Admin page</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/adminPage.css"/> "/>
</head>
<body>
<c:set var="teacher" value="${sessionScope.teacher}"  />

<table border="1" width="900" align="center" cellspacing="5" cellpadding="10">
    <tr>
        <th colspan="5">
          <h1>${teacher.lastName} ${teacher.firstName} ${teacher.middleName}</h1>  </th>
    </tr>
    <tr align="center">
        <td colspan="5"><h1><fmt:message key="label.action.choose"/> </h1></td>
    </tr>
    <tr align="center">
        <td> <form method="get" action="/setAttributeForScheduleRecord">
            <input type=submit  value="<fmt:message key="button.add.schedule"/> " >
        </form>
        </td>
        <td> <form method="post" action="/setAttributeForTeacherRegister">
            <input type=submit  value="<fmt:message key="button.add.teacher"/>" >
        </form>
        </td>
        <td> <form method="post" action="/setAttributeForStudentRegister">
            <input type=submit  value="<fmt:message key="button.add.student"/>" >
        </form>
        </td>
    </tr>
</table>
<a href="<c:url value="teacher.jsp"/> "> <fmt:message key="ref.return.teacherPage"/> </a>
<br/> <br/>
<a href="<c:url value="index.jsp"/> "> <fmt:message key="ref.exit"/> </a>
</body>
</html>
