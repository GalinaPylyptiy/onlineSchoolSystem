<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose locale</title>
</head>
<body>
<fmt:setBundle basename="locale"/>
<form action="/getLocaleList" method="get">
    <input type="submit" value="<fmt:message key="msg.choose.locale"/> ">
</form>
</body>
</html>
