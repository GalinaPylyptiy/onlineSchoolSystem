<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<style>
    body {
        background-image: url('img/backgroundTeacher.jpg');
        background-size: 100% 100%;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
</style>
<head>
    <title>Teacher`s page</title>

</head>
<body>

<c:set var="teacher" value="${sessionScope.teacher}"  />
<c:set var="teacherSubjectList" value="${sessionScope.teacherSubjectList}"/>
<c:set var="admin" value="${teacher.isAdmin}" scope="session"/>

<table border="1" width="900" align="center" cellspacing="5" cellpadding="10">
    <tr>
        <th colspan="5">
         <fmt:message key="msg.teacher.name"/> ${teacher.lastName} ${teacher.firstName} ${teacher.middleName}
    </tr>
    <tr align="center">
        <td colspan="5"> <fmt:message key="label.action.choose"/></td>
    </tr>
        <tr align="center">
            <td> <form method="post" action="/setAttributeForCurriculumRecord">
             <input type=submit  value="<fmt:message key="button.add.curriculum"/>" >
            </form> </td>
            <td> <form method="post" action="/setAttributeForAssessmentRecord">
                <input type=submit  value="<fmt:message key="button.add.assessment"/> "  >
            </form> </td>
            <td> <form method="post" action="/getAttributeForTeacherSchedule">
            <input type=submit  value="<fmt:message key="button.watch.schedule"/>" >
        </form> </td>
        </tr>
</table>
     <c:if test="${admin}" var="admin" scope="session">
         <a href="<c:url value="admin.jsp"/> "> <fmt:message key="button.admin.role"/></a>
     </c:if>
   <br/> <br/>
    <a href="<c:url value="index.jsp"/> "> <fmt:message key="ref.exit"/> </a>
</body>
</html>
