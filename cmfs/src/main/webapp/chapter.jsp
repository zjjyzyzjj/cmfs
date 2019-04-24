<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
    $("#tbody").jqGrid({
        url:"/chapter/findAll",
        styleUI:'Bootstrap',
        datatype:"json",
        autowidth:true,
        height:true,
        pager:"#albumpager",
        rowNum:5,
        rowList:[5,10,15,20],
        viewrecords:true,
        colNames:["章节名称","专辑名称","章节路径","章节大小","章节时长","章节播放数",
        "上传时间","操作"],
        colModel:[
            {name:"name",index:"name",sorttype:"String"},
            {name:'zalbum.name',index:"zalbum.name",editable:true},
            {name:"audioPath"},
            {name:"audioSize"},
            {name:"audiotime"},
            {name:"plays"},
            {name:"uptime"},
            {
                name:"option",
                formatter: function (value,options,row) {
                    var result="";
                    result +="<a href='javascript:;' onclick='play(\""+row.id+"\",\""+row.audioPath+"\")' class='btn btn-xs green'" +
                        " title='在线播放'><span class='glyphicon glyphicon-play'></span></a>";
                    result +="<a href='/chapter/download?name="+row.audioPath+"' onclick='updateDowloadCount(\""+row.audioPath+"\")' class='btn btn-xs blue' title='下载'><span class='glyphicon glyphicon-download-alt'></span></a>";
                    result +="<a href='javascript:;' onclick='deleteById(\""+row.id+"\")' class='btn btn-xs red' title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
                    return result;
                }
            }
        ],
        sortname:"zalbum.name",
        grouping:true,
        groupingView:{
            groupField:["zalbum.name"]
        },
        caption:"章节分组展示"
    });
    /*  音频播放*/
    function play(id,audioPath){
            $("#audioInfo").attr("src",audioPath);
        $.post("/chapter/updatePlayCount",{id:id},function(result){
            $("#tbody").jqGrid().trigger("reloadGrid");
        })
        $("#myModal").modal('show');
    }
    /* 更改音频下载次数*/
    function updateDowloadCount(name){
        $.post("/chapter/download",{name:name},function(){
            $("#tbody").jqGrid().trigger("reloadGrid");
        });


    }
    function deleteById(id) {
        $.post("/chapter/delete",
            {"id":id},
                "json"
        );
       jQuery("#tbody").jqGrid('delRowData',id);
    }
    $(function () {
        $("#editAlbumInfo").click(function () {
            var datas = new FormData($("#editForm")[0]);//将表单数据转换为对象,使其可以在流上传输
          //  alert(datas);
            $.ajax({url:"/chapter/insert",
                data:datas,
                contentType:false,//防止contentType冲突
                processData:false,
                type:"post",
                success:function (result) {
                    $("#tbody").jqGrid().trigger("reloadGrid");
                    $("#albumInfo").modal("hide");
                }
            });
            //每次关闭模态框之后 刷新缓存
            $("#editForm")[0].reset();
        });
        function init() {
            $.post("/album/allname",
                    function (result) {
                     $.each(result,function (i,album) {
                         var option = $("<option/>").attr("value",album.id).text(album.name);
                         $("#aubumId").append(option);
                     })
                    },
                "json"
            );
        }
        init();
    });
</script>
<ul class="nav nav-tabs">
    <li class="active"><a href="javascript:;">章节列表</a></li>
    <li><a href="#albumInfo" data-toggle="modal">章节添加</a></li>
</ul>
<%--<a href='javascript:;' onclick='play("+row.id+","+row.audioPath+")' class='btn btn-xs green' title='在线播放'><span class='glyphicon glyphicon-play'></span></a>--%>
<div>
<table id="tbody">
</table>
    <div id="albumpager"></div>
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
                        <label class="control-label col-xs-2">章节名称</label>
                        <div class="col-xs-8">
                            <input type="text" placeholder="请输入标题" name="name" id="albumname />" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">专辑名称</label>
                        <div class="col-xs-8">
                            <select name="aubumId" id="aubumId" class="form-control">



                            </select>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-2">音频文件</label>
                        <div class="col-xs-8">
                            <input type="file" placeholder="请选择音频" name="file" id="fi" class="form-control">

                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="editAlbumInfo">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--展示音频播放器--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">好音乐，随身听~~~</h4>
            </div>
            <div class="modal-body">

                <audio id="audioInfo" src="" controls="controls" autoplay="autoplay">

                </audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>