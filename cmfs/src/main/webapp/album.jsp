<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    #file_upload1 {
        display: none;
    }
    #file_upload1_label {
        display: inline-block;
        border: 1px solid #aaa;
        width: 120px;
        height: 145px;
        margin-left: 20px;
        text-align: center;
        line-height: 145px;
        cursor: pointer;
    }
</style>
<script>
    $(function () {
        init();
    })
    function clearForm(form) {
        // input清空
        $(':input', form).each(function () {
            var type = this.type;
            var tag = this.tagName.toLowerCase(); // normalize case
            if (type === 'text' || type === 'password' || type === 'hidden')
                this.value = "";
            //select 下拉框清空
            else if (tag === 'select')
                this.selectedIndex = -1;
            //将回显的图片缓存清空
            $("#uploadimg").attr("src","");
        });
    };
    /*添加*/
    $("#editInfo").click(function () {
        var formData = new FormData($("#editForm")[0]);
        $.ajax({
            url: "/album/update",
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            success: function () {
                $("#table").jqGrid().trigger("reloadGrid");
            }
        });
        $("#albumInfo").modal('hide');
    });
    /*查看轮播图*/
    function see(id) {
        $.post("/album/queryById",{id:id},function (result) {
            $("#img").attr("src",result.imgPath);
        },"JSON")
        $("#showInfo").modal('show');
    }
    /*删除轮播图*/
    function del(id) {
        $.post("/album/deleteById?id="+id,function (){
            $("#table").jqGrid().trigger("reloadGrid");
        });
    };
    function sv() {
        var datas = new FormData($("#editForm")[0]);//将表单数据转换为对象,使其可以在流上传输
        $.ajax({url:"/album/update",
            data:datas,
            contentType:false,//防止contentType冲突
            processData:false,
            type:"post",
            success:function (result) {
                $("#table").jqGrid().trigger("reloadGrid");
                $("#albumInfo").modal("hide");
            }
        });
    };
    /!*查找一个并赋值给模态框*!/
    function queryById(id) {
        $.post("/album/queryById",
            {"id":id},
            function (result) {
                $("#id").val(result.id);
                $("#name").val(result.name);
                $("#score").val(result.score);
                $("#beam").val(result.beam);
                $("#author").val(result.author);
                $("#summary").val(result.summary);
                $("#issueDate").val(result.issueDate);
                $("#count").val(result.count);
                $("#status").val(result.status);
            },
            "json"
        );
        $("#albumInfo").modal("show");
    }
    /*初始化数据*/
    function init(){
        $("#table").jqGrid({
            caption:"专辑列表",
            url: "${pageContext.request.contextPath}/album/findAll",
            datatype: "json",
            autowidth: true,
            pager: "#pager",
            rowList:[2,4,6,8,10],//用来指定下拉列表中每页显示条数
            viewrecords: true,
            rowNum: 5,
            styleUI: 'Bootstrap',
            colNames: ["id", "专辑名称","专辑评分", "专辑作者","专辑播音员", "章节数量","专辑内容", "专辑封面","上传时间","专辑状态","操作"],
            colModel: [
                {name: "id",},
                {name: "name",},
                {name: "score",},
                {name: "author",},
                {name: "beam",},
                {name: "count",},
                {name: "summary",},
                {name: "imgPath",editable:true,
                    formatter: function (value,row,index) {
                        var content="<img src=\""+index.imgPath+"\" width='80px' height='50px'></img>"
                        return content;
                    }},
                {name: "issueDate",},
                {name: "status",editable:true,
                    formatter: function (value,row,index) {
                        if (value == "激活") {
                            return "激活";
                        } else if (value == "冻结") {
                            return "冻结";
                        }
                    }},
                {name:"options",
                    //二次渲染出删除与修改按钮
                    formatter:function (value,options,row) {
                        var content="<a onclick=\"javascript:see('"+row.id+"')\"><span class='glyphicon glyphicon-search'></span></a>&nbsp;&nbsp;" +
                            "<a onclick=\"javascript:queryById('"+row.id+"')\"><span class='glyphicon glyphicon-pencil'></span></a>&nbsp;&nbsp;" +
                            "<a onclick=\"javascript:del('"+row.id+"')\"><span class='glyphicon glyphicon-remove'></span></a>";
                        return content;
                    }
                }
            ],
        });
    }
</script>
<div class="row">
    <div class="col-sm-12">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#" aria-controls="home" role="tab" data-toggle="tab">专辑列表</a></li>
            <li role="presentation"><a id="save" aria-controls="profile" role="tab" data-toggle="modal" href="#albumInfo" onclick="clearForm();">专辑添加</a>
            </li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="home">
                <%-- 创建一个表格--%>
                <table id="table"></table>
                <%--创建分页--%>
                <div id="pager" style="height: 50px"></div>
            </div>
        </div>
    </div>
</div>
<!--添加修改模态框-->
<div class="modal fade" id="albumInfo" tabindex="-1" role="dialog">
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
                        <label class="control-label col-xs-2">专辑名称</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="name" id="name"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">专辑分数</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="score" id="score" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">专辑播音</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="beam" id="beam" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">专辑作者</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="author" id="author" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">专辑描述</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="summary" id="summary" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">上传时间</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="issueDate" id="issueDate" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">章节数量</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="count" id="count" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">专辑封面</label>
                        <div class="col-xs-8">
                            <input type="file" placeholder="请选择图片" name="image" id="image" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label">专辑状态</label>
                        <div class="col-xs-8">
                            <select name="status" id="status" class="form-control">
                                <option value="激活" <c:if test=""></c:if>>>激活</option>
                                <option value="未激活">冻结</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="editAlbumInfo" onclick="sv()">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="showInfo" role="dialog">
    <div class="modal-dialog" role="document" style="width:800px;height:600px;" >
        <div class="modal-content">
            <!--标题-->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" ><span aria-hidden="true">&times;</span></button>
                <h3>展示专辑封面图</h3>
            </div>
            <!--内容-->
            <div class="modal-body">
                <img id="img" style="width:700px;height:400px; overflow: hidden" />
            </div>
        </div>
    </div>
</div>
<script>
    function upload_review() {
        var img = document.getElementById("uploadimg");
        var input = document.getElementById("file_upload1");
        var tip = document.getElementById("uploadtip");
        var file = input.files.item(0);
        var freader = new FileReader();
        freader.readAsDataURL(file);
        freader.onload = function(e) {
            img.src = e.target.result;
            tip.style.display = "none";
        };
    }
</script>

