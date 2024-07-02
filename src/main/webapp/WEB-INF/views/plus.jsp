<%@ page import="com.example.digitalsample.service.PlusService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PLUS</title>
</head>
<body>
<%
    PlusService plusService = new PlusService();
    int x = 1;
    int y = 2;
    int sum = plusService.plus(x, y);
%>
<h1>더하기</h1>
<p>
    <%=x%> + <%=y%> = <%=sum%>
</p>
</body>
</html>