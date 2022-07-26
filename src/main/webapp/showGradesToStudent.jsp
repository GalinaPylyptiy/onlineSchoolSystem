<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>My grades</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>

<c:set var="student" value="${sessionScope.student}"/>
<c:set var="subject" value="${sessionScope.subject}"/>
<c:set var="assessmentList"  value="${sessionScope.assessmentList}"/>
<c:set var="averageGrade" value="${sessionScope.averageGrade}"/>

<table border="1" width="700" align="center" cellspacing="5" cellpadding="5">
    <caption align="center"><h3>${student.lastName} ${student.firstName}</h3></caption>
        <tr><td colspan="7" align="center" >${subject.name}</td></tr>
    <tr>
        <td><fmt:message key="label.lessonDate"/> </td>
        <td><fmt:message key="label.lessonTheme"/> </td>
        <td><fmt:message key="label.grade"/> </td>
    </tr>
        <c:forEach var="assessment" items="${assessmentList}">
      <tr>
        <td>
            <fmt:parseDate var="lessonDate" value="${assessment.curriculumRecord.lessonDate}" pattern="yyyy-MM-dd" type="date" dateStyle="short"/>
            <fmt:formatDate var="formatedDate" value="${lessonDate}" pattern="dd.MM.yyyy" type="date" dateStyle="short"/>
                ${formatedDate}
        </td>
         <td>
             ${assessment.curriculumRecord.lessonTheme}
         </td>
         <td align="center">
             ${assessment.grade}
         </td>
      </tr>
        </c:forEach>
      <tr>
          <td colspan="2">
              <fmt:message key="label.avg.grade"/>
          </td>
          <td>
              ${averageGrade}
          </td>
      </tr>

</table>
<a href="<c:url value="student.jsp"/> "><fmt:message key="ref.return.main.page"/> </a>
</body>
</html>
