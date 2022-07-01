<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Registration Portal</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
</head>

<body>

<!-- create navigation bar ( header) -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/login">Registration & Login Module</a>
        </div>
    </div>
</nav>

<br>
<br>

<h1>User Login Page</h1>
<form action="/registration" href="/registration" method="post">
    <div class="form-group">
        <label class="control-label" for="firstName"> First Name </label>
        <@spring.formInput "userRegistrationDto.firstName"/>
    </div>

    <div class="form-group">
        <label class="control-label" for="lastName"> Last Name </label>
        <@spring.formInput "userRegistrationDto.lastName"/>
    </div>

    <div class="form-group">
        <label class="control-label" for="email"> Email </label>
        <@spring.formInput "userRegistrationDto.email"/>
    </div>

    <div class="form-group">
        <label class="control-label" for="password"> Password </label>
        <@spring.formInput "userRegistrationDto.password"/>
    </div>

    <div class="form-group">
        <label class="control-label" for="roles"> User Role </label>
        <#assign differentUsers = ['ADMIN', 'DEVELOPER', 'HR']>
        <@spring.formSingleSelect "userRegistrationDto.roles", differentUsers, " "/>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-success">Register</button>
        <span>Already registered? <a href="/login">Login
								here</a></span>
    </div>
</form>
</body>
</html>