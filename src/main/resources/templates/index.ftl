<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Portal</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h1>Logged In</h1>
<h2>First Name: ${user.firstName}</h2>
<h2>Last Name: ${user.lastName}</h2>
<h2>Email: ${user.email}</h2>
<h2>Roles: ${user.roles}</h2>

<#assign differentUsers = []>

<#list message.users as pairs>
        <#assign differentUsers += ["${user.email} & ${pairs.getEmail()}"]>
</#list>

<h2>Messages Dashboard</h2>
<form action="/index" method="post">
    <div class="form-group">
        <label class="control-label" for="roles"> Choose a User </label>
        <@spring.formSingleSelect "userRegistrationDto.email", differentUsers, " "/>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-success">Message</button>
    </div>
</form>
<br><br><br><br>
<form action="/logout" method="get">
    <div class="form-group">
        <button type="submit" class="btn btn-success">Logout!</button>
    </div>
</form>


<script src="/js/main.js"></script>
</body>
</html>