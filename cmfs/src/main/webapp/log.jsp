<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {
        init();
    })

    /*初始化数据*/
    function init(){
        $("#table").jqGrid({
            caption:"日志列表",
            url: "/log/findAll",
            datatype: "json",
            autowidth: true,
            pager: "#pager",
            rowList:[2,4,6,8,10],//用来指定下拉列表中每页显示条数
            viewrecords: true,
            rowNum: 5,
            styleUI: 'Bootstrap',
            colNames: ["id", "账号","修改内容", "操作时间","是否成功"],
            colModel: [
                {name: "id",},
                {name: "name",},
                {name: "method",},
                {name: "operateTime",},
                {name: "yesornot",},
            ],
        });
    }
    $(function () {
        $("#export").click(function () {
            $.post("/log/export",
                function (result) {
                    alert("导出成功");
                },
                "json");
        })
        $("#import").click(function () {
            $.post("/log/import",
                function (result) {
                    alert("导入成功");
                },
                "json");
        })
    })

</script>
<div class="row">
    <div class="col-sm-12">
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="home">
                <%-- 创建一个表格--%>
                <table id="table"></table>
                <%--创建分页--%>
                <div id="pager" style="height: 50px"></div>
                    <button id="export" value="导出日志">导出日志</button>
                    <br>
                    <button id="import" value="导人日志">导人日志</button>
            </div>
        </div>
    </div>
</div>
