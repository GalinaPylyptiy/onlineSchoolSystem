<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>timetable</title>

</head>
<body>

<c:set var="daysOfWeek" value="${sessionScope.daysOfWeek}" />
<c:set var="levels" value="${sessionScope.levelList}" />
<c:set var="subjects" value="${sessionScope.subjectList}" />
<c:set var="teachers" value="${sessionScope.teacherList}" />

<form action="/addScheduleRecord"  method="POST">

    <p> <label><strong><fmt:message key="msg.choose.dayOfWeek"/> </strong></label>:
        <label> <select name ="dayOfTheWeek">
            <c:forEach var="day" items="${daysOfWeek}">
                <option>${day}</option>
            </c:forEach>
        </select> </label></p>

    <p>
        <label><strong><fmt:message key="msg.enter.timeOfLesson"/> </strong>
        <input type="time" name="time"></label>
    </p>

    <p>
        <label><strong><fmt:message key="msg.choose.level"/> </strong></label>:
        <label> <select name ="level">
            <c:forEach var="level" items="${levels}">
                <option>${level.name}</option>
            </c:forEach>
        </select> </label>
    </p>
    <p>
        <strong><fmt:message key="msg.choose.subject"/> </strong>
    </p>
    <label><select name="subject">
      <c:forEach var="subject" items="${subjects}">
        <option>${subject.name}</option>
     </c:forEach>
    </select></label>
    <p>
        <label><strong><fmt:message key="msg.see.teacherList"/> </strong></label></p>
    <label><select name="teacher">
      <c:forEach var="teacher" items="${teachers}">
          <option>${teacher}</option>
      </c:forEach>
        </select></label>
    <p>
       <label><strong ><fmt:message key="msg.choose.teacher"/> </strong></label>
   </p>
    <label><select name ="teacherId">
        <c:forEach var="teacher" items="${teachers}">
            <option>${teacher.id}</option>
        </c:forEach>
    </select></label>

    <p> <input type="submit" name="submit" value="<fmt:message key="button.add.schedule"/> "></p>
</form>

</body>
</html>
