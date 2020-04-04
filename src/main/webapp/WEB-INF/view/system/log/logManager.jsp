<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>日志管理</title>
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
            <button type="button"
                    class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                    id="doSearch" style="margin-top: 4px">查询
            </button>
        </div>
    </div>
</form>

<%--<!-- 数据表格开始 -->--%>
<table class="layui-hide" id="logTable" lay-filter="logTable"></table>
<%--<div id="videosTypeToolBar" style="display: none;">--%>
<%--    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>--%>
<%--    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm layui-btn-radius" lay-event="deleteBatch">--%>
<%--        批量删除--%>
<%--    </button>--%>
<%--</div>--%>
<%--<div id="videosTypeBar" style="display: none;">--%>
<%--    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">编辑</a>--%>
<%--    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>--%>
<%--</div>--%>

<!-- 添加和修改的弹出层-->

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
            elem: '#logTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/log/loadAllLog.action' //数据接口
            , title: '数据表'//数据导出来的标题
            , toolbar: "#logToolBar"   //表格的工具条
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', align: 'center'}
                , {field: 'username', title: '操作人', align: 'center'},
                , {field: 'module', title: '操作模块', align: 'center'}
                , {field: 'content', title: '操作内容', align: 'center'}
                , {field: 'created', title: '创建时间', align: 'center', templet: "<div>{{layui.util.toDateString(d.ordertime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"}
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
                url: "${pageContext.request.contextPath}/log/loadAllLog.action?" + params,
                page: {curr: 1}
            })
        });


    });

</script>
</body>
</html>

