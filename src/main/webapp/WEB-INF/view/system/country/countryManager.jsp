<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>国家管理</title>
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
            <label class="layui-form-label">国家名称:</label>
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
<table class="layui-hide" id="countryTable" lay-filter="countryTable"></table>
<div id="countryToolBar" style="display: none;">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm layui-btn-radius" lay-event="deleteBatch">
        批量删除
    </button>
</div>
<div id="countryBar" style="display: none;">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewImage">查看大图</a>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form layui-row layui-col-space10" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-col-md12 layui-col-xs12">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md9 layui-col-xs7">
                    <input type="hidden" name="id">
                    <div class="layui-form-item">
                        <label class="layui-form-label">国家名称:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="name" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">国家编号:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="number" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">竞争力:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <select name="competitiveness" lay-filter="aihao">
                                <option value=""></option>
                                <option value="较差">较差</option>
                                <option value="中等">中等</option>
                                <option value="有竞争力">有竞争力</option>
                                <option value="很有竞争力">很有竞争力</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md3 layui-col-xs5">
                    <div class="layui-upload-list thumbBox mag0 magt3" id="countryimgDiv">
                        <%--显示要上传的图片--%>
                        <img class="layui-upload-img thumbImg" id="showCountryImg">
                        <%--保存当前显示图片的地址--%>
                        <input type="hidden" name="imgage" id="countryimg">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">签证要求:</label>
                <div class="layui-input-block">
                    <select name="visarequired" lay-filter="aihao">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
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

<%--查看大图弹出的层开始--%>
<div id="viewCountryImageDiv" style="display: none;text-align: center">
    <img alt="国家图片" width="700px" height="450px" id="view_countryimg">
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
            elem: '#countryTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/country/loadAllCountry.action' //数据接口
            , title: '数据表'//数据导出来的标题
            , toolbar: "#countryToolBar"   //表格的工具条
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'name', title: '国家名称', align: 'center', width: '190'}
                , {field: 'number', title: '编号', align: 'center', width: '190'}
                , {field: 'competitiveness', title: '竞争力', align: 'center', width: '190'}
                , {field: 'visarequired', title: '签证要求', align: 'center', width: '200'}
                , {
                    field: 'countryimg', title: '缩略图', align: 'center', width: '90', templet: function (d) {
                        return "<img width=40 height=40 src=${pageContext.request.contextPath}/file/downloadShowFile.action?path=" + d.imgage + "/>";
                    }
                }
                , {fixed: 'right', title: '操作', toolbar: '#countryBar', align: 'center', width: '220'}
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
                url: "${pageContext.request.contextPath}/country/loadAllCountry.action?" + params,
                page: {curr: 1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(countryTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddCountry();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(countryTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.name + '】这个国家么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/country/deleteCountry.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateCountry(data);
            }else if (layEvent === 'viewImage'){ //查看大图
                showCountryImage(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddCountry() {
            mainIndex = layer.open({
                type: 1,
                title: '添加国家',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //设置默认图片
                    $("#showCountryImg").attr("src", "${pageContext.request.contextPath}/file/downloadShowFile.action?path=images/defaultcountryimage.jpg");
                    $("#countryimg").val("images/defaultcountryimage.jpg");
                    url = "${pageContext.request.contextPath}/country/addCountry.action";
                }
            });
        }

        //打开修改页面
        function openUpdateCountry(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改国家',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    $("#showCountryImg").attr("src", "${pageContext.request.contextPath}/file/downloadShowFile.action?path=" + data.imgage);
                    url = "${pageContext.request.contextPath}/country/updateCountry.action";
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
            var checkStatus = table.checkStatus('countryTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.id;
                } else {
                    params += "&ids=" + item.id;
                }
            });
            layer.confirm('真的要删除这些国家么？', function (index) {
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/country/deleteBatchCountry.action", params, function (res) {
                    layer.msg(res.msg);
                    //刷新数据表格
                    tableIns.reload();
                })
            });
        }

        //上传缩略图
        upload.render({
            elem: '#countryimgDiv',
            url: '${pageContext.request.contextPath}/file/uploadFile.action',
            method: "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            acceptMime: 'images/*',
            field: "mf",
            done: function (res, index, upload) {
                $('#showCountryImg').attr('src', "${pageContext.request.contextPath}/file/downloadShowFile.action?path=" + res.data.src);
                $('#countryimg').val(res.data.src);
                $('#countryimgDiv').css("background", "#fff");
            }
        });
        
        //查看大图
        function showCountryImage(data) {
            mainIndex = layer.open({
                type: 1,
                title: "【"+data.name+'】的国家图片',
                content: $("#viewCountryImageDiv"),
                area: ['750px', '500px'],
                success: function (index) {
                    $("#view_countryimg").attr("src","${pageContext.request.contextPath}/file/downloadShowFile.action?path="+data.imgage);
                }
            });
        }

    });

</script>
</body>
</html>

