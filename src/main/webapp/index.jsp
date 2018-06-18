<%--
  Created by IntelliJ IDEA.
  User: joel
  Date: 2018/2/24
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Joel control</title>
</head>
<script type="text/javascript" language="JavaScript">

    function a(){
        document.getElementById('form3').submit();
    }
</script>
<body>

<form id = 'startTrade' action="http://localhost:8080/springBootProj/trade/startTrade" method="get">
    <input type = "submit" value = "开启"><br>
</form>
<form id = 'closeTrade' action="http://localhost:8080/springBootProj/trade/closeTrade" method="get">
    <input type = "submit" value = "关闭"><br>
</form>
</body>
</html>

