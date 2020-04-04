<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>收藏管理</title>
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
            <div class="layui-inline">
                <label class="layui-form-label">视频名称:</label>
                <div class="layui-input-inline" style="padding: 5px">
                    <input type="text" name="videoName" id="videoName" class="layui-input layui-input-inline"
                            style="height: 30px;border-radius: 10px">
                </div>
            </div>
            <c:if test="${userType == 1}">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名称:</label>
                    <div class="layui-input-inline" style="padding: 5px">
                        <input type="text" name="username" id="username"  class="layui-input layui-input-inline"
                                style="height: 30px;border-radius: 10px">
                    </div>
                </div>
            </c:if>
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
<table class="layui-hide" id="collectTable" lay-filter="collectTable"></table>

<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'upload','laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;
        var dtree = layui.dtree;
        var upload = layui.upload;

        //渲染数据表格
        tableIns = table.render({
            elem: '#collectTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/collect/loadAllCollect.action' //数据接口
            , title: '数据表'//数据导出来的标题
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: '编号', align: 'center'}
                , {field: 'videoName', title: '视频名称', align: 'center'}
                , {field: 'username', title: '用户名称', align: 'center'}
                , {field: 'created', title: '创建时间', align: 'center', templet: "<div>{{layui.util.toDateString(d.created, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"}
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
            tableIns.reload({
                url: "${pageContext.request.contextPath}/collect/loadAllCollect.action?" + params,
                page: {curr: 1}
            })
        });

    });

</script>
</body>
</html>

