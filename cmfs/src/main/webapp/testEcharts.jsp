<!doctype html>
<%@ page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="./statics/echarts/echarts.min.js"></script>
    <script src="./statics/boot/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'testEcharts 男女比例'
        },
        tooltip: {},
        legend: {
            data:['男女比例']
        },
        xAxis: {
            data: ["男","女"]
        },
        yAxis: {},
        series: [{
            name: '男女比例',
            type: 'bar',
            data: [60, 40,]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
   /* $.get('./testjson1.json').done(function (data) {
        myChart.setOption({

            series: [{

                data: data.data
            }]
        });
    });*/

    /*展示专辑表的图片*/
    function testEcharts() {

        $.get("./testjson1.json",
            function (result) {
                myChart.setOption({

                    series: [{

                        data: result.data
                    }]
                });
            },
            "json"
        );
    }
    testEcharts();
</script>
</body>
</html>