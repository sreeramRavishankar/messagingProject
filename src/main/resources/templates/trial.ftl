<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Portal</title>
    <style><#include "trial.css"></style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Message: ${user.getReceiverEmail()}</h1>
    </div>

    <div class="body">
        <#list messages.getMessages() as pairs>
            <#if pairs.getEmail() == user.getEmail()>
                <p class="message user_message">
                    Message: ${pairs.getTextMessage()}
                    <br>
                    (At: ${pairs.getTimeStamp()})
                </p>
            <#else>
                <p class="message">
                    Message: ${pairs.getTextMessage()}
                    <br>
                    (At: ${pairs.getTimeStamp()})
                </p>
            </#if>
        </#list>
    </div>

    <div class="footer">
        <form action="/trial" method="post">
            <@spring.formInput "messageRegistrationDto.textMessage"/>
            <button type="submit" class="btn btn-success">Send</button>
        </form>
    </div>
</div>
</body>
</html>


