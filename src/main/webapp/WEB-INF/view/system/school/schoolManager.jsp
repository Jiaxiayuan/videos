<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>学校管理</title>
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
            <label class="layui-form-label">学校名称:</label>
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
            <div class="layui-inline">
                <label class="layui-form-label">所属城市</label>
                <div class="layui-input-inline">
                    <select name="cityid" id="searchcitymgr">
                    </select>
                </div>
            </div>
            <div class="layui-inline">
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
            <div class="layui-inline">
                <label class="layui-form-label">签证要求:</label>
                <div class="layui-input-block">
                    <select name="visarequired" lay-filter="aihao">
                        <option value=""></option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">城市类型:</label>
                <div class="layui-input-block" style="padding: 5px">
                    <select name="citytype" lay-filter="aihao">
                        <option value=""></option>
                        <option value="较大城市">较大城市</option>
                        <option value="大城市">大城市</option>
                        <option value="中等城市">中等城市</option>
                        <option value="小城市">小城市</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学校类型:</label>
                <div class="layui-input-block" style="padding: 5px">
                    <select name="type" lay-filter="aihao">
                        <option value=""></option>
                        <option value="城市的">城市的</option>
                        <option value="郊区的">郊区的</option>
                        <option value="农村的">农村的</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">宿舍类型:</label>
                <div class="layui-input-block" style="padding: 5px">
                    <select name="dorm" lay-filter="aihao">
                        <option value=""></option>
                        <option value="校内宿舍">校内宿舍</option>
                        <option value="校外民宿">校外民宿</option>
                        <option value="校内宿舍和校外民宿">校内宿舍和校外民宿</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">学期:</label>
                <div class="layui-input-block" style="padding: 5px">
                    <select name="vacation" lay-filter="aihao">
                        <option value=""></option>
                        <option value="整学年">整学年</option>
                        <option value="一学期">一学期</option>
                        <option value="二学期">二学期</option>
                        <option value="暑假学期">暑假学期</option>
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
<table class="layui-hide" id="schoolTable" lay-filter="schoolTable"></table>
<c:if test="${userType == 1}">
<div id="schoolToolBar" style="display: none;">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm layui-btn-radius" lay-event="deleteBatch">
        批量删除
    </button>
</div>
</c:if>
<div id="schoolBar" style="display: none;">
    <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="show">查看</a>
<c:if test="${userType == 1}">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
</c:if>
</div>


<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form layui-row layui-col-space10" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-col-md12 layui-col-xs12">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md9 layui-col-xs7">
                    <input type="hidden" name="id">
                    <div class="layui-form-item">
                        <label class="layui-form-label">学校名称:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="name" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">所属国家</label>
                            <div class="layui-input-inline">
                                <select name="countryid" id="mgr" lay-filter="mgr">
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属城市</label>
                            <div class="layui-input-inline">
                                <select name="cityid" id="citymgr">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">地址:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="address" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">类型:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <select name="type" lay-filter="aihao">
                                <option value=""></option>
                                <option value="城市的">城市的</option>
                                <option value="郊区的">郊区的</option>
                                <option value="农村的">农村的</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">宿舍:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <select name="dorm" lay-filter="aihao">
                                <option value=""></option>
                                <option value="校内宿舍">校内宿舍</option>
                                <option value="校外民宿">校外民宿</option>
                                <option value="校内宿舍和校外民宿">校内宿舍和校外民宿</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学期:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <select name="vacation" lay-filter="aihao">
                                <option value=""></option>
                                <option value="整学年">整学年</option>
                                <option value="一学期">一学期</option>
                                <option value="二学期">二学期</option>
                                <option value="暑假学期">暑假学期</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">电话:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="phone" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">网址:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="website" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">学校简介:</label>
                        <div class="layui-input-block">
                            <textarea placeholder="请输入内容" name="information" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">补充信息:</label>
                        <div class="layui-input-block">
                            <textarea placeholder="请输入内容" name="description" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学费:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="tuition" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">名额:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="quota" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
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
            elem: '#schoolTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/school/loadAllSchool.action' //数据接口
            , title: '数据表'//数据导出来的标题
            , toolbar: "#schoolToolBar"   //表格的工具条
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'name', title: '学校名称', align: 'center'}
                , {field: 'countryname', title: '国家', align: 'center'}
                , {field: 'cityname', title: '城市', align: 'center'}
                , {field: 'address', title: '地址', align: 'center'}
                , {field: 'type', title: '类型', align: 'center'}
                , {field: 'dorm', title: '宿舍', align: 'center'}
                , {field: 'vacation', title: '假期', align: 'center'}
                , {field: 'phone', title: '电话', align: 'center'}
                , {field: 'website', title: '网址', align: 'center'}
                , {field: 'information', title: '简介', align: 'center'}
                , {field: 'description', title: '补充信息', align: 'center'}
                , {field: 'tuition', title: '学费', align: 'center'}
                , {field: 'quota', title: '名额', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#schoolBar', align: 'center',width:'200'}
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
        //监听下拉
        form.on('select(mgr)', function(data) {
            $.get("${pageContext.request.contextPath}/city/loadAllCityJson.action",{countryid:data.value},function(res){
                var users=res.data;
                var dom_mgr=$("#citymgr");
                var html="<option value='0'>请选择</option>";
                $.each(users,function(index,item){
                    html+="<option value='"+item.id+"'>"+item.name+"</option>";
                });
                dom_mgr.html(html);
                //重新渲染
                form.render("select");
            });
        })

        form.on('select(searchmgr)', function(data) {
            $.get("${pageContext.request.contextPath}/city/loadAllCityJson.action",{countryid:data.value},function(res){
                var users=res.data;
                var dom_mgr=$("#searchcitymgr");
                var html="<option value='0'>请选择</option>";
                $.each(users,function(index,item){
                    html+="<option value='"+item.id+"'>"+item.name+"</option>";
                });
                dom_mgr.html(html);
                //重新渲染
                form.render("select");
            });
        })


            //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
//            alert(params);
            tableIns.reload({
                url: "${pageContext.request.contextPath}/school/loadAllSchool.action?" + params,
                page: {curr: 1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(schoolTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddSchool();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(schoolTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.name + '】这个学校么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/school/deleteSchool.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateSchool(data);
            }else if (layEvent === 'show'){ //查看大图
                window.location = "${pageContext.request.contextPath}/sys/toSchoolShowManager.action?id="+data.id;
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddSchool() {
            mainIndex = layer.open({
                type: 1,
                title: '添加学校',
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


                    url = "${pageContext.request.contextPath}/school/addSchool.action";
                }
            });
        }

        //打开修改页面
        function openUpdateSchool(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改学校',
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
                    var cityid=data.cityid;
                    $.get("${pageContext.request.contextPath}/city/loadAllCityJson.action",{countryid:countryid},function(res){
                        var users=res.data;
                        var dom_citymgr=$("#citymgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                        dom_citymgr.html(html);
                        //重新渲染
                        dom_citymgr.val(data.cityid);
                        form.render("select");
                    });


                    url = "${pageContext.request.contextPath}/school/updateSchool.action";
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
            var checkStatus = table.checkStatus('schoolTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.id;
                } else {
                    params += "&ids=" + item.id;
                }
            });
            layer.confirm('真的要删除这些学校么？', function (index) {
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/school/deleteBatchSchool.action", params, function (res) {
                    layer.msg(res.msg);
                    //刷新数据表格
                    tableIns.reload();
                })
            });
        }

        //上传缩略图
        upload.render({
            elem: '#schoolimgDiv',
            url: '${pageContext.request.contextPath}/file/uploadFile.action',
            method: "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            acceptMime: 'images/*',
            field: "mf",
            done: function (res, index, upload) {
                $('#showSchoolImg').attr('src', "${pageContext.request.contextPath}/file/downloadShowFile.action?path=" + res.data.src);
                $('#schoolimg').val(res.data.src);
                $('#schoolimgDiv').css("background", "#fff");
            }
        });
        
        //查看大图
        function showSchoolImage(data) {
            mainIndex = layer.open({
                type: 1,
                title: "【"+data.name+'】的学校图片',
                content: $("#viewSchoolImageDiv"),
                area: ['750px', '500px'],
                success: function (index) {
                    $("#view_schoolimg").attr("src","${pageContext.request.contextPath}/file/downloadShowFile.action?path="+data.imgage);
                }
            });
        }

    });

</script>
</body>
</html>

