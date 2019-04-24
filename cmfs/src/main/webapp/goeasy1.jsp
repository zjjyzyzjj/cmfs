<!doctype html>
<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="./statics/boot/js/jquery-3.3.1.min.js"></script>
    <title>Document</title>
    <script>
        $(function () {
            var goEasy = new GoEasy({
                appkey: "BC-2b66fbf505a54de1a1ca0b060dc1be20"
            });
            $("#btn").click(function () {
                goEasy.publish({
                    channel: "my_channel",
                    message: $("#ti").val()
                });
            });



            goEasy.subscribe({
                channel: "my_channel",
                onMessage: function (message) {
                    alert("Channel:" + message.channel + " content:" + message.content);
                }
            });
        })


    </script>
</head>
<body>
    <form>
        请输入聊天内容: <br>
        <input type="text" id="ti">
        <br>
        <input type="button" id="btn" value="发送">
    </form>
</body>
</html>