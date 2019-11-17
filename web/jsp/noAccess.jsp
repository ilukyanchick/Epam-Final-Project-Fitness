<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="no_access" var="no_access"/>

<html>
<head>
    <title>Restricted</title>
</head>
<body>
<h1>${no_access}</h1>
<a href="${pageContext.servletContext.contextPath}/login" style="position: absolute;margin-top: 250px;margin-left: 680px;background: white;color: black;font-weight: 700;width: 84px;height: 20px;text-shadow: none;border-radius: 5px;">Return to Login Page</a>
</body>
</html>