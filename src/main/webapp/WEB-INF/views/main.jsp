<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>main</title>
    <style>
        body{margin: 0 15%;}
    </style>
</head>
<body>
<div>
    <p>Enter User name:</p>
    <form method="GET" action="/addUser">
        <input type="text" name="name"> <br />
        <input type="submit" value="Add User">
    </form>
</div>
<%--<div>
    <p>viewName:</p>
    <form method="GET" action="/viewName">
        <input type="text" name="value"> <input type="submit" value="add">
    </form>
</div>--%>
</body>
</html>