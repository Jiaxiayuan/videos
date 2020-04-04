<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>专业课程管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

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
            <label class="layui-form-label">所属国家</label>
            <div class="layui-input-inline">
                <select name="countryid" id="searchmgr" lay-filter="searchmgr">
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">所属城市</label>
            <div class="layui-input-inline">
                <select name="cityid" id="searchcitymgr" lay-filter="searchcitymgr">
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">学校名称</label>
            <div class="layui-input-inline">
                <select name="schoolid" id="searchschoolmgr" lay-filter="searchschoolmgr">
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">专业名称</label>
            <div class="layui-input-inline">
                <select name="schoolmajorid" id="searchmajormgr">
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
</form>

<!-- 数据表格开始 -->
<table class="layui-hide" id="majorCourseTable" lay-filter="majorCourseTable"></table>
<div id="majorCourseToolBar" style="display: none;">
<c:if test="${userType == 1}">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm layui-btn-radius" lay-event="deleteBatch">
        批量删除
    </button>
</c:if>
</div>
<div id="majorCourseBar" style="display: none;">
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
                        <div class="layui-inline">
                            <label class="layui-form-label">所属国家</label>
                            <div class="layui-input-inline">
                                <select name="countryid" id="countrymgr" lay-filter="countrymgr">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">所属城市</label>
                            <div class="layui-input-inline">
                                <select name="cityid" id="citymgr" lay-filter="citymgr">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">学校名称</label>
                            <div class="layui-input-inline">
                                <select name="schoolid" id="schoolmgr" lay-filter="schoolmgr">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">专业名称</label>
                            <div class="layui-input-inline">
                                <select name="schoolmajorid" id="majormgr">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">所属课程</label>
                            <div class="layui-input-inline">
                                <select name="courseid" id="mgr" lay-filter="mgr">
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">级别:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="grade" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">信用等级:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="credit" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">简介:</label>
                        <div class="layui-input-block">
                            <textarea placeholder="请输入内容" name="description" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学期:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="term" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">分数:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="fraction" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">作业权重:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="task" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">目标:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="target" autocomplete="off" class="layui-input"
                                   placeholder="请输入" style="height: 30px;border-radius: 10px">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学习成果:</label>
                        <div class="layui-input-block" style="padding: 5px">
                            <input type="text" name="gain" autocomplete="off" class="layui-input"
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
            elem: '#majorCourseTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/majorCourse/loadAllMajorCourse.action' //数据接口
            , title: '数据表'//数据导出来的标题
            , toolbar: "#majorCourseToolBar"   //表格的工具条
            , height: 'full-210'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'majorname', title: '专业名称', align: 'center'}
                , {field: 'coursename', title: '课程名称', align: 'center'}
                , {field: 'description', title: '简介', align: 'center'}
                , {field: 'grade', title: '级别', align: 'center'}
                , {field: 'credit', title: '信用等级', align: 'center'}
                , {field: 'term', title: '学期', align: 'center'}
                , {field: 'fraction', title: '分数', align: 'center'}
                , {field: 'task', title: '作业权重', align: 'center'}
                , {field: 'target', title: '目标', align: 'center'}
                , {field: 'gain', title: '学习成果', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#majorCourseBar', align: 'center'}
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

        form.on('select(searchcitymgr)', function(data) {
            $.get("${pageContext.request.contextPath}/school/loadSchoolJson.action",{cityid:data.value},function(res){
                var users=res.data;
                var dom_mgr=$("#searchschoolmgr");
                var html="<option value='0'>请选择</option>";
                $.each(users,function(index,item){
                    html+="<option value='"+item.id+"'>"+item.name+"</option>";
                });
                dom_mgr.html(html);
                //重新渲染
                form.render("select");
            });
        })

        form.on('select(searchschoolmgr)', function(data) {
            $.get("${pageContext.request.contextPath}/schoolMajor/loadSchoolMajorJson.action",{schoolid:data.value},function(res){
                var users=res.data;
                var dom_mgr=$("#searchmajormgr");
                var html="<option value='0'>请选择</option>";
                $.each(users,function(index,item){
                    html+="<option value='"+item.id+"'>"+item.majorname+"</option>";
                });
                dom_mgr.html(html);
                //重新渲染
                form.render("select");
            });
        })



        form.on('select(countrymgr)', function(data) {
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

        form.on('select(citymgr)', function(data) {
            $.get("${pageContext.request.contextPath}/school/loadSchoolJson.action",{cityid:data.value},function(res){
                var users=res.data;
                var dom_mgr=$("#schoolmgr");
                var html="<option value='0'>请选择</option>";
                $.each(users,function(index,item){
                    html+="<option value='"+item.id+"'>"+item.name+"</option>";
                });
                dom_mgr.html(html);
                //重新渲染
                form.render("select");
            });
        })

        form.on('select(schoolmgr)', function(data) {
            $.get("${pageContext.request.contextPath}/schoolMajor/loadSchoolMajorJson.action",{schoolid:data.value},function(res){
                var users=res.data;
                var dom_mgr=$("#majormgr");
                var html="<option value='0'>请选择</option>";
                $.each(users,function(index,item){
                    html+="<option value='"+item.id+"'>"+item.majorname+"</option>";
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
                url: "${pageContext.request.contextPath}/majorCourse/loadAllMajorCourse.action?" + params,
                page: {curr: 1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(majorCourseTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddMajorCourse();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(majorCourseTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                layer.confirm('真的删除这个专业课程么？', function (index) {
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/majorCourse/deleteMajorCourse.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //编辑
                //编辑，打开修改界面
                openUpdateMajorCourse(data);
            }else if (layEvent === 'viewImage'){ //查看大图
                showMajorCourseImage(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddMajorCourse() {
            mainIndex = layer.open({
                type: 1,
                title: '添加专业',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    $.get("${pageContext.request.contextPath}/country/loadAllCountryJson.action",function(res){
                        var users=res.data;
                        var dom_mgr=$("#countrymgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                        dom_mgr.html(html);
                        //重新渲染
                        form.render("select");
                    });


                    $.get("${pageContext.request.contextPath}/course/loadAllCourseJson.action",function(res){
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
                    url = "${pageContext.request.contextPath}/majorCourse/addMajorCourse.action";
                }
            });
        }

        //打开修改页面
        function openUpdateMajorCourse(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改专业课程',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '480px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    var courseid=data.courseid;
                    $.get("${pageContext.request.contextPath}/course/loadAllCourseJson.action",function(res){
                        var users=res.data;
                        var dom_mgr=$("#mgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                        dom_mgr.html(html);
                        dom_mgr.val(courseid);
                        //重新渲染
                        form.render("select");
                    });
                    var countryid=data.countryid;
                    $.get("${pageContext.request.contextPath}/country/loadAllCountryJson.action",function(res){
                        var users=res.data;
                        var dom_mgr=$("#countrymgr");
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
                        var dom_mgr=$("#citymgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                        dom_mgr.html(html);
                        dom_mgr.val(cityid);

                        //重新渲染
                        form.render("select");
                    });
                    var schoolid=data.schoolid;
                    $.get("${pageContext.request.contextPath}/school/loadSchoolJson.action",{cityid:cityid},function(res){
                        var users=res.data;
                        var dom_mgr=$("#schoolmgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                        dom_mgr.html(html);
                        dom_mgr.val(schoolid);
                        //重新渲染
                        form.render("select");
                    });
                    var schoolmajorid=data.schoolmajorid;
                    $.get("${pageContext.request.contextPath}/schoolMajor/loadSchoolMajorJson.action",{schoolid:schoolid},function(res){
                        var users=res.data;
                        var dom_mgr=$("#majormgr");
                        var html="<option value='0'>请选择</option>";
                        $.each(users,function(index,item){
                            html+="<option value='"+item.id+"'>"+item.majorname+"</option>";
                        });
                        dom_mgr.html(html);
                        dom_mgr.val(schoolmajorid);
                        //重新渲染
                        form.render("select");
                    });

                    url = "${pageContext.request.contextPath}/majorCourse/updateMajorCourse.action";
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
            var checkStatus = table.checkStatus('majorCourseTable');
            var data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.id;
                } else {
                    params += "&ids=" + item.id;
                }
            });
            layer.confirm('真的要删除这些专业课程么？', function (index) {
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/majorCourse/deleteBatchMajorCourse.action", params, function (res) {
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

