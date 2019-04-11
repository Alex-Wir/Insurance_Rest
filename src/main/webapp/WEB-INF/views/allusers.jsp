<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All Users</title>
        <style>
            body {
                margin: 0 15%;
            }
            td {
                border: solid 1px black;
            }
        </style>
    </head>
    <body>
    <a href="/addUser">Return to main page</a>
    <script>
        function printTable(j) {
            var table = document.createElement('table');
            var str = "";
            for (var i = 1; i < j; i++) {
                str += "<tr>1</tr>";
            }
            table.innerHTML = str;
            table.display;
            document.write("<br />");
            document.write(str);
            document.write("<br />");
            document.write("1231231232321");
            document.write("<br />");
            document.write("Number of users: " + j);
        }
       printTable(${number});
    </script>
    </body>
</html>
