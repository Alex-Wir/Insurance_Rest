<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>All Users</title>
        <style>
            body {
                margin: 0 15%;
            }
            table, td {
                border: solid 1px black;
            }
            td{
                width:100px;
                height:20px;
            }
        </style>
    </head>
    <body>
    <p id="userstable"></p>
    <a href="/main">Return to main page</a>
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
    </script>
    </body>
</html>
