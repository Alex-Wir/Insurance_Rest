<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>All Users</title>
        <style>
            body {
                margin: 0 15%;
            }
            table, td, th {
                border: solid 1px black;
            }
            td{
                width:100px;
                height:20px;
            }
            td.id{
                width:20px;
            }
        </style>
    </head>
    <body>
    <h2>Users list</h2>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td class="id">${user.id}</td>
                    <td>${user.name}</td>
                </tr>
            </c:forEach>
        </tbody>
     </table>

<%--    <p id="userstable"></p>
    <a href="/adduser">Return to main page</a>
    <script>
        function printTable(j) {
            var str = "<table>";
            for (var i = 1; i <= j; i++) {
                str += "<tr><td>" + i + " user </td></tr>";
            }
            str+="</table>";
            return(str);
        }
        document.getElementById("userstable").innerHTML = printTable(${number});
    </script>--%>
    </body>
</html>
