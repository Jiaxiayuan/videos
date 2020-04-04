<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
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
<style>
    body {
        background: #f2f2f2;
    }

    .video {
        display: block;
        width: 100%;
        height: auto;
    }

    .margin15 {
        margin: 15px;
    }
</style>

<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>

<body>
<div class="layui-container">
    <div class="layui-row layui-col-space15 margin15">
        <section class="layui-card">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend id="title"></legend>
            </fieldset>
            <div class="video"  style="padding-left: 30px;">
                视频描述: <span id="descs"></span>
                <br />
                <br />
                <button type="button"
                        class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                        id="next" style="margin-top: 4px">下一个
                </button>
                <button type="button"
                        class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                        id="collect" style="margin-top: 4px">收藏
                </button>
            </div>
            <div class="layui-card-body">
                <div class="video" id="video" data-url=""></div>
            </div>
        </section>
    </div>
</div>
</body>
</html>


<script type="text/javascript">
    layui.config({
        base: '../static/'
    }).extend({
        ckplayer: 'ckplayer/ckplayer'
    }).use(['jquery', 'ckplayer'], function () {
        var $ = layui.$,
            ckplayer = layui.ckplayer;

        $("#next").click(function () {
            location.reload();
        });

        $("#collect").click(function () {
            var videoId = window.localStorage.getItem("videoId");
            $.get("${pageContext.request.contextPath}/collect/addCollect.action?videoId=" + videoId, function (res) {
                $("#collect").text('取消收藏');
            });
        });

        $.get("${pageContext.request.contextPath}/client/fetchNextVideo.action", function (res) {
            var video = res.obj;
            console.log(res);
            if (video != null) {
                window.localStorage.setItem("videoId", video.id);
                if (video.collect) {
                    $("#collect").text('取消收藏');
                } else {
                    $("#collect").text('收藏');
                }
                $("#title").text(video.name);
                $("#descs").html(video.descs);
                var videoObject = {
                    container: '#video',
                    loop: true,
                    autoplay: true,
                    video: [
                        ["/myvideos/" + video.path, 'video/mp4']
                    ]
                };
                var player = new ckplayer(videoObject);
                var t2 = window.setInterval(function () {
                    $.get("${pageContext.request.contextPath}/client/checkLikeVideo.action?videoId=" + video.id, function (obj) {
                        console.log("checkLikeVideo执行成功！");
                    });
                },3000);
            } else {
                alert(res.msg);
            }
        });
    });
</script>

