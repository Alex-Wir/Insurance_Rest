<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add user</title>
    <style>
        body{margin: 0 15%;}
    </style>
</head>
<body>
<div>
    <h2>Create new user</h2>
    <form method="GET" action="/useradded">
        <label for="name">Enter user name:</label>
        <input type="text" name="name" id="name"><br />
        <p><input type="submit" value="Add User">
    </form>
</div>
<div>
    <a href="/viewusers">View all users</a>
</div>
</body>
</html>