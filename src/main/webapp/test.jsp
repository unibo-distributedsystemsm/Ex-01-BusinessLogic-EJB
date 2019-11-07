<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SDM Web Demo</title>
</head>
<body>
<p>Say <a href="hello">Hello to Wildfly!</a> (with get request)</p>
<br/>
<form method="post" action="hello" id="hello-form">
    <h2>Name:</h2>
    <input type="text" id="say-hello-text-input" name="name" />
    <input type="submit" id="say-hello-button" value="Say Hello (with post request)" />
</form>
<br/>
<form method="post" action="hibernate" id="hibernate-form">
    <input type="submit" id="hibernate-button" value="!!Read Employees from DB!!!" />
</form>
</body>
</html>
