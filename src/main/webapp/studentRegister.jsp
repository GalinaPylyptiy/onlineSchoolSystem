<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>student register</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="css/commonPage.css"/> "/>
</head>
<body>

<c:set var="levelList" value="${requestScope.levelList}"/>
<form  action="/studentRegister"  method="POST">
    <p><label><strong><fmt:message key="msg.enter.lastName"/> </strong><input type="text" name="last name" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.enter.firstName"/> </strong><input type="text" name="first name" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.enter.middleName"/> </strong><input type="text" name="middle name" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.login"/> </strong>:<input type="text" name="login" size="30" maxlength="20"></label></p>
    <p><label><strong><fmt:message key="msg.password"/> </strong>:<input type="password" name="password" size="30" maxlength="30" ></label></p>
    <p><label><strong ><fmt:message key="msg.choose.level"/> </strong></label></p>
    <label><select name ="level">
        <c:forEach var="level" items="${levelList}">
            <option>${level.name}</option>
        </c:forEach>
    </select></label>
    <p> <input type="submit" name="submit" value="<fmt:message key="button.add.student"/> "></p>
</form>
<a href="<c:url value="admin.jsp"/> "> <fmt:message key="button.cancel"/> </a>
</body>
</html>