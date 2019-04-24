<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
    $(function () {
        initData();
        //轮播图添加
        $("#editInfo").click(function () {
            var datas = new FormData($("#editForm")[0]);//将表单数据转换为对象,使其可以在流上传输
            $.ajax({url:"/turn/insert",
                    data:datas,
                    contentType:false,//防止contentType冲突
                    processData:false,
                    type:"post",
                    success:function (result) {
                        initData();
                        $("#userInfo").modal("hide");
                    }
            });
        });
    });
    //轮播图查询所有
    function initData(){
        $("#tbody tr:not(:first)").empty();
        $.post(
            "/turn/findAll",
            function (result) {
                $.each(result,function (i,zTurn) {
                    var tr = $("<tr/>");
                    var titletd = $("<td/>").text(zTurn.title);
                    var imgpathtd = $("<td/>").text(zTurn.imgPath);
                    var imgdesctd = $("<td/>").text(zTurn.imgDesc);
                    var statustd = $("<td/>").text(zTurn.status);
                    var uptimetd = $("<td/>").text(zTurn.uptime);
                    var imgatd = $("<td/>").text(zTurn.imga);
                    // var data = zTurn.imgPath;
                    var optd = $("<td/>").html("<a href='' onclick='showPic(\""+zTurn.imgPath+"\")' data-toggle='modal'><span class='glyphicon glyphicon-search'></span></a>\n" +
                        "<a href='javascript:;' onclick='selectById(\""+zTurn.id+"\")'><span class='glyphicon glyphicon-pencil'></span></a>" +
                        "<a href='javascript:;' onclick='deleteById(\""+zTurn.id+"\")'><span class='glyphicon glyphicon-remove'></span></a>");
                    tr.append(titletd).append(imgpathtd).append(imgdesctd).append(statustd).append(uptimetd).append(imgatd).append(optd);
                    $("#tbody").append(tr);
                });
            },
            "json"
        );
    }
    function selectById(id) {
        $.post("turn/queryById",
            {"id":id},
            function (result) {
                $("#id").val(result.id);
                $("#title").val(result.title);
                $("#desc").val(result.imgDesc);
                $("#description").val(result.status);
                var options = $("#status").find("option");
                for (var i = 0;i<options.length;i++){
                    if ($(options[i]).val()==result.status){
                        $(options[i]).attr("selected","selected");
                    }else {
                        $(options[i]).removeAttr("selected");
                    }
                }
            },
            "json"

        );
        $('#userInfo').modal("show");
    }
    function showPic(imgPath) {
       // alert(imgPath);
        $("#imgsrc").attr("src",imgPath);
        $("#showPic").modal("show");
    }
    function deleteById(e) {
        var id=e;
        $.post("turn/delete",
            {"id":id},
            function (result) {
             },
            "json"
        );
        initData();
    }
</script>
<ul class="nav nav-tabs">
    <li class="active"><a href="javascript:;">轮播图列表</a></li>
    <li><a href="#userInfo" data-toggle="modal">轮播图添加</a></li>
</ul>
<div>
<table class="table table-bordered" id="tbody">
    <tr>
        <td>图片名称</td>
        <td>图片路径</td>
        <td>图片描述</td>
        <td>状态</td>
        <td>上传时间</td>
        <td>图片超链接</td>
        <td>操作</td>
    </tr>
</table>
    <div id="paper"></div>
</div>
<!--添加修改模态框-->
<div class="modal fade" id="userInfo" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑图片信息</h4>
            </div>
            <div class="modal-body">
                <form  id="editForm" class="form-horizontal" enctype="multipart/form-data">
                    <input type="hidden" name="id" id="id" />
                    <div class="form-group">
                        <label class="control-label col-xs-2">图片标题</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="title" id="title" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">选择图片</label>
                        <div class="col-xs-8">
                            <input type="file" placeholder="请选择图片" name="image" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">图片描述</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入图片描述" name="imgDesc" id="desc" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">图片状态</label>
                        <div class="col-xs-8">
                            <select name="status" id="status" class="form-control">
                                <option value="激活">激活</option>
                                <option value="冻结">冻结</option>
                            </select>
                        </div>

                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="editInfo">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--查找一个图片模态框--%>
<div class="modal fade" id="showPic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body">
                <img src="" alt="" id="imgsrc" width="550px">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
