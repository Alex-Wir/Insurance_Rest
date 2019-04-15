<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit user</title>
    <style>
        body{
            margin: 0 15%;
        }
    </style>
</head>
<body>
    <h2>Edit user</h2>
    <form:form method="POST" action="../userupdate">
        <table >
            <tr>
                <td></td>
                <td><form:hidden  path="id" /></td>
            </tr>
            <tr>
                <td>Name : </td>
                <td><form:input type="text" path="name"  /></td>
            </tr>
            <tr>
                <td> </td>
                <td><input type="submit" value="Edit user" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
