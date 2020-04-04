<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>城市管理</title>
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
</head>
<body class="childrenBody">

<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">城市名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="name" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入" style="height: 30px;border-radius: 10px">
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">所属国家</label>
                <div class="layui-input-inline">
                    <select name="countryid" id="searchmgr" lay-filter="searchmgr">
                    </select>
                </div>
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
<table class="layui-hide" id="cityTable" lay-filter="cityTable"></table>
<div id="cityToolBar" style="display: none;">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm layui-btn-radius" lay-event="deleteBatch">
        批量删除
    </button>
</div>
<div id="cityBar" style="display: none;">
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
                        <label class="layui-form-label">城市名称:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="name" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">城市类型:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <select name="type" lay-filter="aihao">
                                <option value=""></option>
                                <option value="较大城市">较大城市</option>
                                <option value="大城市">大城市</option>
                                <option value="中等城市">中等城市</option>
                                <option value="小城市">小城市</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">城市介绍:</label>
                        <div class="layui-input-block">
                            <textarea placeholder="请输入内容" name="description" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属国家</label>
                        <div class="layui-input-inline">
                            <select name="countryid" id="mgr">
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item magb0">
                <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                    <button type="button"
                            class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                            lay-filter="doSubmit" lay-submit="">提交
                    </button>
                    <button type="reset"
                            class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">
                        重置
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
        $.get("${pageContext.request.contextPath}/country/loadAllCountryJson.action",function(res){
            var users=res.data;
            var dom_mgr=$("#searchmgr");
            var html="<option value='0'>请选择</option>";
            $.each(users,function(index,item){
                html+="<option value='"+item.id+"'>"+item.name+"</option>";
            });
            dom_mgr.html(html);
            //重新渲染
            form.render("select");
        });


        //渲染数据表格
        tableIns = table.render({
            elem: '#cityTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/city/loadAllCity.action' //数据接口
            , title: '数据表'//数据导出来的标题
            , toolbar: "#cityToolBar"   //表格的工具条
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', align: 'center'}
                , {field: 'name', title: '城市名称', align: 'center'}
                , {field: 'type', title: '类型', align: 'center'}
                , {field: 'countryname', title: '所属国家', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#cityBar', align: 'center'}
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



        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
//            alert(params);
            tableIns.reload({
                url: "${pageContext.request.contextPath}/city/loadAllCity.action?" + params,
                page: {curr: 1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(cityTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddCity();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(cityTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.name + '】这个城市么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/city/deleteCity.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateCity(data);
            }else if (layEvent === 'viewImage'){ //查看大图
                showCityImage(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddCity() {
            mainIndex = layer.open({
                type: 1,
                title: '添加城市',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    $.get("${pageContext.request.contextPath}/country/loadAllCountryJson.action",function(res){
                        var users=res.data;
                        var dom_mgr=$("#mgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                        dom_mgr.html(html);
                        //重新渲染
                        form.render("select");
                    });


                    url = "${pageContext.request.contextPath}/city/addCity.action";
                }
            });
        }

        //打开修改页面
        function openUpdateCity(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改城市',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    var countryid=data.countryid;
                    $.get("${pageContext.request.contextPath}/country/loadAllCountryJson.action",function(res){
                        var users=res.data;
                        var dom_mgr=$("#mgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                        dom_mgr.html(html);
                        dom_mgr.val(countryid);
                        //重新渲染
                        form.render("select");
                    });
                    url = "${pageContext.request.contextPath}/city/updateCity.action";
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
            var checkStatus = table.checkStatus('cityTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.id;
                } else {
                    params += "&ids=" + item.id;
                }
            });
            layer.confirm('真的要删除这些城市么？', function (index) {
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/city/deleteBatchCity.action", params, function (res) {
                    layer.msg(res.msg);
                    //刷新数据表格
                    tableIns.reload();
                })
            });
        }

        //上传缩略图
        upload.render({
            elem: '#cityimgDiv',
            url: '${pageContext.request.contextPath}/file/uploadFile.action',
            method: "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            acceptMime: 'images/*',
            field: "mf",
            done: function (res, index, upload) {
                $('#showCityImg').attr('src', "${pageContext.request.contextPath}/file/downloadShowFile.action?path=" + res.data.src);
                $('#cityimg').val(res.data.src);
                $('#cityimgDiv').css("background", "#fff");
            }
        });
        
        //查看大图
        function showCityImage(data) {
            mainIndex = layer.open({
                type: 1,
                title: "【"+data.name+'】的城市图片',
                content: $("#viewCityImageDiv"),
                area: ['750px', '500px'],
                success: function (index) {
                    $("#view_cityimg").attr("src","${pageContext.request.contextPath}/file/downloadShowFile.action?path="+data.imgage);
                }
            });
        }

    });

</script>
</body>
</html>

