<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法洲后台管理系统</title>
    <link rel="stylesheet" href="./statics/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="./statics/boot/js/jquery-3.3.1.min.js"></script>
    <script src="./statics/boot/js/bootstrap.min.js"></script>
    <script src="./statics/jqgrid/js/trirand/jquery.jqGrid.min(1).js"></script>
    <script src="./statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script>

    </script>
</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!--导航标题-->
        <div class="navbar-header ">
            <a href="javascript:location.reload(true);" class="navbar-brand">持明法洲 <small>V1.0</small></a>
        </div>
        <!--导航内容-->
        <ul class="nav navbar-nav navbar-right">
            <li><a >欢迎: <span class="text-primary">消费</span></a></li>
            <li><a >退出登录 <span class="glyphicon glyphicon-log-out"></span></a></li>
        </ul>
    </div>
</nav>

<!--栅格系统-->
<div class="container-fluid">
    <div class="row">

        <!--左边-->
        <div class="col-sm-2" >

            <!--手风琴-->
            <div class="panel-group" id="accordion">

                <!--轮播图模块-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <a href="#aa" data-toggle="collapse" data-parent="#accordion">轮播图模块</a>
                        </div>
                    </div>

                    <div id="aa" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLayout').load('/lunbotu.jsp')" >轮播图管理</a></li>

                            </ul>
                        </div>
                    </div>

                </div>

                <!--专辑模块-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <a href="#ee" data-toggle="collapse" data-parent="#accordion" >专辑模块</a>
                        </div>
                    </div>
                    <div id="ee" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLayout').load('/album.jsp')">专辑管理</a></li>
                                <li><a href="javascript:$('#centerLayout').load('/chapter.jsp')">章节管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--文章模块-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <a href="#bb" data-toggle="collapse" data-parent="#accordion" >文章模块</a>
                        </div>
                    </div>
                    <div id="bb" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="">文章管理</a></li>

                            </ul>
                        </div>
                    </div>
                </div>
                <!--用户模块-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <a href="#cc" data-toggle="collapse" data-parent="#accordion" >用户模块</a>
                        </div>
                    </div>
                    <div id="cc" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLayout').load('/china.jsp')">用户管理</a></li>

                            </ul>
                        </div>
                    </div>
                </div>

                <!--日志模块-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <a href="#dd" data-toggle="collapse" data-parent="#accordion" >日志模块</a>
                        </div>
                    </div>
                    <div id="dd" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLayout').load('/log.jsp')">日志管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <!--右边-->
        <div class="col-sm-10" id="centerLayout">

            <!--服务器状态-->
            <div class="panel panel-default">

                <div class="panel-heading">
                    <div class="panel-title">
                        <h5>欢迎登录持明法洲管理系统</h5>
                    </div>
                </div>


            </div>
            <!--巨幕-->
            <div class="jumbotron">
                <img src="/imag/u=865281090,960288146&fm=26&gp=0.jpg" alt="" width="1000">
            </div>






        </div>
    </div>
</div>
</body>
</html>
