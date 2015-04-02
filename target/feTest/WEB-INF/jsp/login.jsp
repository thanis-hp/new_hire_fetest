<%--
  Created by IntelliJ IDEA.
  User: rajagova
  Date: 8/16/12
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%  /*
    String result;
    String loginUsername = request.getParameter("loginUsername");
    if (null != loginUsername && loginUsername.length() > 0) {
        if (loginUsername.equals("f"))
            result = "{success:true}";
        else
            result = "{success:false,errors:{reason:'Login failed.Try again'}}";

    } else {
        result = "{success:false,errors:{reason:'Login failed.Try again'}}";
    }        */
%>


<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/scripts/ext.js/4.1.1/resources/css/ext-all.css">
    <script type="text/javascript" src="<%=contextPath%>/scripts/ext.js/4.1.1/ext-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/scripts/ext.js/Login.js"></script>

</head>
<body>

</body>
</html>