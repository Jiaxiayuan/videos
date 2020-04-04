<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>学校</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css" media="all"/>
</head>
<body>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12 content detail">
            <div class="fly-panel detail-box">${avc}
                <h1>${school.name }</h1>
                <hr>
                <div class="fly-detail-info">
                    <p><h2>网址</h2></p>
                    <h3><a href="${school.website }">${school.website }</a></h3>
                </div>
                <hr>
                <div class="detail-body photos">
                    <p><h2>城市信息</h2></p>
                    <p>
                        ${city.description }
                    </p>
                    <hr>
                    <p><h2>大学信息</h2></p>
                    <p>
                        ${school.information }
                    </p>
                    <hr>
                    <p><h2>附加信息</h2></p>
                    <p>
                        ${school.description }
                    </p>



                    <hr>
                    <p><h2>汇总</h2></p>
                    <table id="tblSummary">
                        <tbody>
                        <tr>
                            <th>竞争力</th>
                            <td>${country.competitiveness }</td>
                        </tr>

                        <tr>
                            <th>开放申请名额</th>
                            <td>${school.quota }</td>
                        </tr>

                        <tr>
                            <th>是否需要签证?</th>
                            <c:if test="${country.visarequired ==1}">
                                <td>是</td>
                            </c:if>
                            <c:if test="${country.visarequired==0 }">
                                <td>否</td>
                            </c:if>
                        </tr>

                        <tr>
                            <th>
                                每学期学费
                            </th>
                            <td>${school.tuition }</td>
                        </tr>

                        <tr>
                            <th>城市类型</th>
                            <td>${city.type }</td>
                        </tr>

                        <tr>
                            <th>校园类型</th>
                            <td>${school.type }</td>
                        </tr>

                        <tr>
                            <th>交换生住宿</th>
                            <td>${school.dorm }</td>
                        </tr>
                        <tr>
                            <th>学期安排?</th>
                            <td>${school.vacation }</td>
                        </tr>
                        </tbody>
                    </table>
                    <hr>
                    <p><h1>课程介绍</h1></p>

                    <c:forEach items="${courses}" var="course">
                        <p>${course.name }</p>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
