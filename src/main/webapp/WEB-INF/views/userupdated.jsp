<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User updated</title>
    <style>
        body{margin: 0 15%;}
    </style>
</head>
<body>
    <div>
        <p>User <b>${user.name}</b> updated!</p>
    </div>
    <a href="/adduser">Add new user</a>
    <br />
    <a href="/viewusers">View all users</a>
</body>
</html>
