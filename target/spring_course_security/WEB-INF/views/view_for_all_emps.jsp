<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: michel
  Date: 15.02.2021
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All employees</title>
</head>

<body>

    <h2>Information for all employees</h2>
    <security:authorize access="hasRole('HR')">
        <input type="button" value="Salary"
               onclick="window.location.href= 'hr_info' ">
        Only for HR staff
    </security:authorize>

    <br><br>

    <security:authorize access="hasRole('MANAGER')">
        <input type="button" value="Performance"
               onclick="window.location.href= 'manager_info'">
        Only for managers
    </security:authorize>



</body>
</html>
