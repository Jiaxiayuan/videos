<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>视频管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <%--<link rel="icon" href="favicon.ico">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/fm.tagator.jquery.css" media="all"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/fm.tagator.jquery.js"></script>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css"></link>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/plupload/js/plupload.full.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/plupload/js/i18n/zh_CN.js"></script>
</head>
<style type="text/css">
    body {
        font-family: sans-serif;
        margin: 0;
        padding: 0;
    }

    #wrapper {
        width: 600px;
        margin: 100px auto;
    }

    .inputTagator {
        width: 400px;
        border: 1px solid #abadb3;
        border-radius: 3px;
        box-sizing: border-box;
        background-color: #fff;
        display: inline-block;
        outline: none;
    }

    .operateBtn {
        padding: 6px;
    }
</style>
<script type="text/javascript">

</script>
<body class="childrenBody">

<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">视频名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="name" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入" style="height: 30px;border-radius: 10px">
            </div>
            <button type="button"
                    class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                    id="doSearch" style="margin-top: 4px">查询
            </button>
            <button type="reset"
                    class="layui-btn layui-btn-warm layui-icon layui-icon-refresh layui-btn-radius layui-btn-sm"
                    style="margin-top: 4px">重置
            </button>
        </div>
    </div>
</form>

<!-- 数据表格开始 -->
<table class="layui-hide" id="videoTable" lay-filter="videoTable"></table>
<div id="videoToolBar" style="display: none;">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm layui-btn-radius" lay-event="deleteBatch">
        批量删除
    </button>
</div>
<div id="videoBar" style="display: none;">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form layui-row layui-col-space10" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-col-md12 layui-col-xs12">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md9 layui-col-xs7">
                    <input type="hidden" name="id">
                    <div class="layui-form-item">
                        <label class="layui-form-label">视频名称:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="name" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">视频简介</label>
                        <div class="layui-input-block">
                            <textarea placeholder="请输入视频简介" class="layui-textarea" name="descs"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">视频分类:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input id="inputTagator" name="types" type="text" autocomplete="off" value="原创"
                                   class="layui-input inputTagator"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                            <input value="激活 tagator" id="activate_tagator" type="hidden" class="operateBtn">
                        </div>
                    </div>
                    <div class="layui-form-item" id="videoCom">
                        <label class="layui-form-label">视频上传:</label>
                        <div class="layui-input-block" style="padding: 5px" id="uploader">

                        </div>
                        <div id="f1"></div>
                    </div>
                </div>

            </div>
            <div class="layui-form-item magb0">
                <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                    <button type="button"
                            class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                            lay-filter="doSubmit" lay-submit="">提交
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'upload'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree = layui.dtree;
        var upload = layui.upload;
        //渲染数据表格
        tableIns = table.render({
            elem: '#videoTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/video/loadAllVideo.action' //数据接口
            , title: '数据表'//数据导出来的标题
            , toolbar: "#videoToolBar"   //表格的工具条
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: '编号', align: 'center'}
                , {field: 'name', title: '视频名称', align: 'center'}
                , {field: 'descs', title: '视频描述', align: 'center'}
                , {field: 'types', title: '视频分类', align: 'center'},
                , {field: 'watched', title: '视频观看数', align: 'center'}
                , {field: 'collected', title: '视频收藏数', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#videoBar', align: 'center'}
            ]],
            done: function (data, curr, count) {
                //不是第一页时，如果当前返回的数据为0那么就返回上一页
                if (data.data.length == 0 && curr != 1) {
                    tableIns.reload({
                        page: {
                            curr: curr - 1
                        }
                    })
                }
            }
        });

        $('#activate_tagator').click(function () {
            if ($('#inputTagator').data('tagator') === undefined) {
                $('#inputTagator').tagator({
                    autocomplete: ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', 'seventh', 'eighth', 'ninth', 'tenth']
                });
            } else {
                $('#inputTagator').tagator('destroy');
            }
        });
        $('#activate_tagator').trigger('click');
        $('#getvalue').click(function () {
            alert($("#inputTagator").val());
        });

        $("#uploader").pluploadQueue({
            runtimes: 'html5,flash,silverlight',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
            url: '${pageContext.request.contextPath}/file/uploadVideo.action',//上传文件路径
            max_file_size: '3gb',//100b, 10kb, 10mb, 1gb
            chunk_size: '1mb',//分块大小，小于这个大小的不分块
            unique_names: true,//生成唯一文件名
            // 如果可能的话，压缩图片大小
            // resize : { width : 320, height : 240, quality : 90 },
            // 指定要浏览的文件类型
            filters: [{
                title: 'Image files',
                extensions: 'jpg,gif,png'
            }, {
                title: 'Zip files',
                extensions: 'zip,7z,rar'
            }, {
                title: 'videos',
                extensions: 'mpg,m4v,mp4,flv,3gp,mov,avi,rmvb,mkv,wmv'
            }],
            init: {
                FileUploaded: function (up, file, info) {//文件上传完毕触发
                    console.info(up);
                    console.info(file);
                    console.info(info);
                    var response = $.parseJSON(info.response);
                    if (response.status) {
                        $('#f1').append('<input type="hidden" name="path" value="' + response.fileUrl + '"/>');
                    }
                }
            }
        });

        var uploader = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'pickfiles', // you can pass an id...
            container: document.getElementById('uploader'), // ... or DOM Element itself
            url: '${pageContext.request.contextPath}/file/uploadVideo.action',//上传文件路径
            max_file_size: '3gb',//100b, 10kb, 10mb, 1gb
            prevent_duplicates: true,
            chunk_size: '1mb',//分块大小，小于这个大小的不分块
            unique_names: true,//生成唯一文件名
            filters: {
                max_file_size: '10mb',
                mime_types: [
                    {
                        title: 'videos',
                        extensions: 'mpg,m4v,mp4,flv,3gp,mov,avi,rmvb,mkv,wmv'
                    }
                ]
            },

            init: {

                FilesAdded: function (up, files) {

                    plupload.each(files, function (file) {
                        document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b><a href="javascript:;" οnclick="dodel(' + "'" + file.id + "'" + ')">x</a></div>';
                    });
                },

                FileUploaded: function (up, file, info) {//文件上传完毕触发
                    var response = $.parseJSON(info.response);
                    if (response.status) {
                        $('#f1').append('<input type="hidden" name="path" value="' + response.fileUrl + '"/>');
                    }
                }
                ,
                UploadProgress: function (up, file) {
                    document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
                },

                Error: function (up, err) {
                    document.getElementById('console').appendChild(document.createTextNode("\nError #" + err.code + ": " + err.message));
                }
            }
        });

        uploader.init();

        function dodel(files) {
            //alert(files);
            //文件数组删除
            uploader.removeFile(files);
            //标签删除
            $("#" + files).remove();
        }

        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
//            alert(params);
            tableIns.reload({
                url: "${pageContext.request.contextPath}/video/loadAllVideo.action?" + params,
                page: {curr: 1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(videoTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddVideo();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(videoTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.name + '】这个视频么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/video/deleteVideo.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateCountry(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddVideo() {
            mainIndex = layer.open({
                type: 1,
                title: '添加视频',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    $('#inputTagator').tagator('destroy');
                    if ($('#inputTagator').data('tagator') === undefined) {
                        $('#inputTagator').tagator({
                            autocomplete: ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', 'seventh', 'eighth', 'ninth', 'tenth']
                        });
                    }
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "${pageContext.request.contextPath}/video/addVideo.action";
                },
                end : function() {
                    location.reload();
                }
            });
        }

        //打开修改页面
        function openUpdateCountry(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改视频',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    $('#inputTagator').tagator('destroy');
                    $("#videoCom").css('display','none');
                    form.val("dataFrm", data);
                    if ($('#inputTagator').data('tagator') === undefined) {
                        $('#inputTagator').val(data.types)
                        $('#inputTagator').tagator({
                            autocomplete: ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', 'seventh', 'eighth', 'ninth', 'tenth']
                        });
                    }
                    url = "${pageContext.request.contextPath}/video/updateVideo.action";
                },
                end: function () {
                    $("#videoCom").css('display','block');
                }
            });
        }

        //保存
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //批量删除
        function deleteBatch() {
            //得到选中的数据行
            var checkStatus = table.checkStatus('videoTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.id;
                } else {
                    params += "&ids=" + item.id;
                }
            });
            layer.confirm('真的要删除这些视频么？', function (index) {
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/video/deleteBatchVideo.action", params, function (res) {
                    layer.msg(res.msg);
                    //刷新数据表格
                    tableIns.reload();
                })
            });
        }



    });

</script>
</body>
</html>

