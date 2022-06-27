<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter some info</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@page isELIgnored="false" %>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<c:set var="teacher" value="${sessionScope.teacher}"/>
<c:set var="subjectList" value="${sessionScope.subjectList}"/>
<c:set  var="levelList" value="${sessionScope.levelList}"/>

<h2>${teacher.id} ${teacher.lastName} ${teacher.firstName}</h2>

<form action="/getStudentsForAssessment" method="post">

    <h3><label><strong><fmt:message key="msg.choose.teacher"/> </strong></label></h3>
    <label> <select name="teacherId">
        <option><c:out value="${teacher.id}"/></option>
    </select></label>

    <h3><label><strong><fmt:message key="msg.choose.subject"/></strong></label></h3>
    <label> <select name="subject">
        <c:forEach var="subject" items="${subjectList}">
            <option><c:out value="${subject.name}"/></option>
        </c:forEach>
    </select></label>

    <h3><label><strong><fmt:message key="msg.choose.level"/></strong></label></h3>
    <label> <select name="level">
        <c:forEach var="level" items="${levelList}">
            <option><c:out value="${level.name}"/></option>
        </c:forEach>
    </select></label>

   <p><input type="submit" name="submit" value="<fmt:message key="button.add.record"/> "></p>
</form>
</body>
</html>
