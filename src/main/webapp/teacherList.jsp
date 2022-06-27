<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Teacher list</title>
</head>
<body>
<c:set var="teacher" value="${sessionScope.newTeacher}"/>
<c:set var="teacherList" value="${sessionScope.teacherList}"/>
<c:set var="subjectLocaleList" value="${sessionScope.subjectLocaleList}"/>

<h1><fmt:message key="msg.successful.record.add"/> </h1>

<h2><fmt:message key="msg.teacher.name"/>${teacher.lastName} ${teacher.firstName} ${teacher.middleName}</h2>
<h2><fmt:message key="label.typeName"/> ${teacher.type.typeName}</h2>

<h2><fmt:message key="msg.subject.name"/>
    <c:forEach var="subjectLocale" items="${subjectLocaleList}">
                      ${subjectLocale.name}
    </c:forEach> </h2>


<table border="1" width="900" align="center" cellspacing="5" cellpadding="10">

    <tr>
        <th>
            <fmt:message key="label.No"/>
        </th>
        <th>
            <fmt:message key="label.lastName"/>
        </th>
        <th>
            <fmt:message key="label.firstName"/>
        </th>
        <th>
            <fmt:message key="label.middleName"/>
        </th>
        <th>
            <fmt:message key="label.typeName"/>
        </th>
        <th>
            <fmt:message key="button.delete.record"/>
        </th>

    </tr>
    <c:forEach var="teacher" items="${teacherList}">
        <tr>
            <td>
                    ${teacher.id}
            </td>
            <td>
                    ${teacher.lastName}
            </td>
            <td>
                    ${teacher.firstName}
            </td>
            <td>
                    ${teacher.middleName}
            </td>
            <td>
                    ${teacher.type.typeName}
            </td>
            <td>
                <a href="/deleteTeacherRecord?teacherId=${teacher.id}">
                    <fmt:message key="button.delete.record"/> <fmt:message key="label.No"/> ${teacher.id}"
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value="admin.jsp"/> "> <fmt:message key="ref.return.main.page"/> </a>
<br/> <br/>
<a href="<c:url value="teacherRegister.jsp"/> "> <fmt:message key="button.add.teacher"/> </a>
</body>
</html>
