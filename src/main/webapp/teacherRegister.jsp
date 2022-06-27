<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Teacher register page</title>
    <style>
        body {
            background-image: url("img/backgroundWelcome.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<c:set var="typeList" value="${requestScope.typeList}"/>
<c:set var="subjectList" value="${requestScope.subjectList}"/>

<form  action="/teacherRegister"  method="POST">
    <p><label><strong><fmt:message key="msg.enter.lastName"/> </strong><input type="text" name="last name" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.enter.firstName"/> </strong><input type="text" name="first name" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.enter.middleName"/> </strong><input type="text" name="middle name" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.login"/> </strong>:<input type="text" name="login" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.password"/> </strong>:<input type="password" name="password" size="30" maxlength="30" ></label></p>
    <p><label><strong ><fmt:message key="msg.choose.teacherType"/> </strong></label></p>
    <label><select name ="teacher type">
            <c:forEach var="type" items="${typeList}">
        <option> ${type.typeName}</option>
            </c:forEach>
    </select></label>
     <p><strong><fmt:message key="msg.choose.subject"/> </strong> </p>
    <c:forEach var="subject" items="${subjectList}">
        <label>
            <input type="checkbox" name="subjects" value="${subject.name}"> <c:out value="${subject.name}"/>
        </label>
    </c:forEach>
    <p><strong><fmt:message key="msg.choose.adminStatus"/> </strong> </p>
    <p><label><input type="radio" name="admin" value="true" checked> <fmt:message key="msg.yes"/> </label></p>
    <p><label> <input type="radio" name="admin" value="false"><fmt:message key="msg.no"/> </label></p>
    <p> <input type="submit" name="submit" value="<fmt:message key="button.add.teacher"/> "></p>
</form>
</body>
</html>
