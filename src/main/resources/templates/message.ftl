<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Portal</title>
    <style><#include "message.css"></style>
</head>
<body>
<h1>Logged In</h1>
<h2>First Name: ${user.firstName}</h2>
<h2>Last Name: ${user.lastName}</h2>
<h2>Email: ${user.email}</h2>
<h2>Roles: ${user.roles}</h2>

<#list message.messageList as conversations>
    <#list conversations.messages as pairs>
        <#if pairs.getEmail() == "ram">
            <p1>From: ${pairs.getEmail()}</p1>
            <p1>To: ${pairs.getReceiverEmail()}</p1>
            <p1>Message: ${pairs.getTextMessage()} (At: ${pairs.getTimeStamp()})</p1>
            <br>
        <#else>
            <p2>From: ${pairs.getEmail()}</p2>
            <p2>To: ${pairs.getReceiverEmail()}</p2>
            <p2>${pairs.getTextMessage()} (At: ${pairs.getTimeStamp()})</p2>
            <br>
        </#if>
    </#list>
    <br> <br>
</#list>

<form action="/logout" method="get">
    <div class="form-group">
        <button type="submit" class="btn btn-success">Logout!</button>
    </div>
</form>


<script src="/js/main.js"></script>
</body>
</html>