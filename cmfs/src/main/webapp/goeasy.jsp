<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法洲后台管理系统</title>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script>
        var goEasy = new GoEasy({
            appkey: "BC-2b66fbf505a54de1a1ca0b060dc1be20"
        });

        goEasy.subscribe({
            channel: "my_channel",
            onMessage: function (message) {
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });

        goEasy.publish({
            channel: "my_channel",
            message: "Hello, GoEasy!"
        });

    </script>
</head>
<body>
Hello World
</body>
</html>
