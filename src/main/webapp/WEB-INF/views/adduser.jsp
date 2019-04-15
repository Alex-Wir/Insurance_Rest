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
    <form method="GET" action="/useradded">
        <input type="text" name="name"> <br />
        <p><input type="submit" value="Add User"></p>
    </form>
</div>
<div>
    <a href="/viewusers">View all users</a>
</div>
</body>
</html>