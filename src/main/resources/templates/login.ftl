<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Portal</title>
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
            <a class="navbar-brand" href="/login">Registration and
                Login Module</a>
        </div>
    </div>
</nav>

<br>
<br>

<div class = "container">
    <div class = "row">
        <div class = "col-md-6 col-md-offset-3">

            <h1>Login Portal</h1>
            <form action="/login" method="post">

                <div class = "form-group">
                    <label for ="username"> Username </label> :
                    <@spring.formInput "userRegistrationDto.email"/>
                </div>
                <div class = "form-group">
                    <label for ="username"> Password </label> :
                    <@spring.formPasswordInput "userRegistrationDto.password"/>
                </div>
                <div class = "form-group">
                    <input type="submit" name="login-submit" id="login-submit"
                           class="form-control btn btn-primary" value="Log In" />
                </div>

                <div class="form-group">
						<span>New user? <a href="/registration">Register
								here</a></span>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>