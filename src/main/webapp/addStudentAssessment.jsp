<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Student Assessment page</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>

<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subject" value="${sessionScope.subject}"/>
<c:set var="level" value="${sessionScope.level}"/>
<c:set var="studentList" value="${sessionScope.studentList}" />
<c:set var="curriculumRecordList" value="${sessionScope.recordList}" />

<a href="<c:url value="teacher.jsp"/> "> <h2><fmt:message key="ref.return.teacherPage"/></h2> </a>

<table border="1" width="700" align="center" cellspacing="5" cellpadding="20">

<caption><h3><fmt:message key="msg.teacher.name"/> ${teacher.lastName}  ${teacher.firstName}  ${teacher.middleName}
<fmt:message key="msg.subject.name"/> ${subject.name}
<fmt:message key="msg.level.name"/> ${level.name} </h3></caption>

    <tr>
        <th colspan="3"><fmt:message key="msg.student.name"/> </th>
        <c:forEach var="record" items="${curriculumRecordList}">
            <th>
                <fmt:parseDate var="lessonDate" value="${record.lessonDate}" pattern="yyyy-MM-dd" type="date" dateStyle="short"/>
                <fmt:formatDate var="formatedDate" value="${lessonDate}" pattern="dd.MM.yyyy" type="date" dateStyle="short"/>
                ${formatedDate}
            </th>
        </c:forEach>
    </tr>

    <c:forEach var="student" items="${studentList}">
        <tr>
            <td colspan="3">
                    ${student.id} ${student.lastName} ${student.firstName}
            </td>
            <c:forEach var="record" items="${curriculumRecordList}">
                <td>
                    <form action="/addAssessmentRecord" method="post">
                        <label><input type="radio" name="recordId"  value="${record.id}" checked hidden></label>
                        <label><input type="radio" name="studentId" value="${student.id}" checked hidden></label>
                        <label>
                            <select name="grade" >
                                <option><fmt:message key="select.grade"/> </option>
                                <option>0</option>  <option>1</option>  <option>2</option>  <option>3</option>
                                <option>4</option>  <option>5</option>  <option>6</option>  <option>7</option>
                                <option>8</option>  <option>9</option>  <option>10</option>
                            </select>
                        </label>
                        <input type="submit"  value="<fmt:message key="button.add"/> ">
                   </form>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value="teacher.jsp"/> "> <fmt:message key="button.cancel"/> </a>
</body>

</html>
