<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Student list</title>
</head>
<body>
<c:set var="studnet" value="${sessionScope.student}"/>
<c:set  var="studentList" value="${sessionScope.studentList}"/>

<h1><fmt:message key="msg.successful.record.add"/> </h1>

<h2><fmt:message key="msg.student.name"/>${studnet.lastName} ${studnet.firstName} ${studnet.middleName}</h2>
<h2><fmt:message key="msg.level.name"/> ${studnet.level.name}</h2>
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
            <fmt:message key="msg.level.name"/>
        </th>

        <th>
            <fmt:message key="button.delete.record"/>
        </th>

    </tr>

    <c:forEach var="studentItem" items="${studentList}">
        <tr>
            <td>
                    ${studentItem.id}
            </td>
            <td>
                    ${studentItem.lastName}
            </td>
            <td>
                    ${studentItem.firstName}
            </td>
            <td>
                    ${studentItem.middleName}
            </td>
            <td>
                    ${studentItem.level.name}
            </td>

            <td>
                <a href="/deleteStudentRecord?studentId=${studentItem.id}">
                    <fmt:message key="button.delete.record"/> <fmt:message key="label.No"/> ${studentItem.id}"
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value="admin.jsp"/> "> <fmt:message key="ref.return.main.page"/> </a>
<br/> <br/>
<a href="/setAttributeForStudentRegister"> <fmt:message key="button.add.student"/> </a>
</body>
</html>
