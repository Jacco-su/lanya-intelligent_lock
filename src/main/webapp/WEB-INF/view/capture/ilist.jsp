<%@ page import="com.dream.brick.equipment.action.CaptureAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%--<script type="text/javascript" src="${basePath}/resources/uploadify/jquery.uploadify.min.js"></script>--%>
<%--<link rel="stylesheet" type="text/css" href="${basePath}/resources/uploadify/uploadify.css" />--%>
<%--<script type="text/javascript" src="${basePath}/resources/js/jquery-1.8.2.min.js"></script>--%>
<html>
<head>
    <title>抓拍图像</title>
    <script type="text/javascript">
    </script>
</head>
<body>
<script>
    /**
     *获取图片文件数组
     */
    function getPicFileList() {
        var params = "/uploads";
        $.ajax({
            //此处使用的是自己封装的JAVA类
            url: "${basePath}/capture/icture",
            type: "POST",
            data: {"params": params},//图片文件夹路径作为参数传入java类
            success: function (data) {
                if (!data.length) {
                    alert("您还没有截图，无法查看图片！");
                    return;
                } else {
                    //获取到的图片数组处理逻辑方法
                    loadPicFormDB(data);
                }

            },
            error: function (e) {
                console.log(e);
                console.log("获取文件list数组失败，请检查接口服务");
            }
        });
    }
    /**
     * 加载图片，将图片拼成html代码
     * @param SJ_CODE 事件编号
     */
    function loadPicFormDB(data) {
        var pichtml = "";
        for (var i = 0; i < data.length; i++) {
            var src = data[i];

//    var html1 = '' + '加载中...' + data[i] + '';
            var html1 = '<li><a  href="file://' + src + '" rel="lightbox" title="' + data[i] + '" target="_blank">'
                + '<img onload="AutoResizeImage(400,200,this)" src="' + src + '"></a><span>' + data[i] + '</span></li>';

            pichtml += html1;
            //scrollPic();
        }
        showPicDetail(pichtml);//展示图片（此代码省略,直接给个p或者弹窗）
        document.write(pichtml);
    }
    /**
     * 按比例缩小图片
     * @param maxWidth
     * @param maxHeight
     * @param objImg
     * @constructor
     */
    function AutoResizeImage(maxWidth, maxHeight, objImg) {
        var img = new Image();
        img.src = objImg.src;
        var hRatio;
        var wRatio;
        var Ratio = 1;
        var w = img.width;
        var h = img.height;
        wRatio = maxWidth / w;
        hRatio = maxHeight / h;
        if (maxWidth == 0 && maxHeight == 0) {
            Ratio = 1;
        } else if (maxWidth == 0) { //
            if (hRatio < 1)
                Ratio = hRatio;
        } else if (maxHeight == 0) {
            if (wRatio < 1)
                Ratio = wRatio;
        } else if (wRatio < 1 || hRatio < 1) {
            Ratio = (wRatio <= hRatio ? wRatio : hRatio);
        }
        if (Ratio < 1) {
            w = w * Ratio;
            h = h * Ratio;
        }
        objImg.height = h;
        objImg.width = w;
    }

    //    function showPicDetail(pichtml){
    //        alert("提示信息！"+ pichtml);
    //        out.println("Your IP address is " + pichtml);
    //    }
</script>

<p>
    <%--<%--%>
    <%--out.println("Your IP address is " + request.getRemoteAddr());--%>
    <%--%>--%>
    <%--<%--%>
    <%--out.println("Your IP address is " + CaptureAction.getFiles("E://192.168.2.2"));--%>
    <%--%>--%>
    <%--<%--%>
    <%--out.println("Your IP address is " + showPicDetail(pichtml));--%>
    <%--%>--%>


<pre name="code" class="java">
    <%
        String test[] = (String[]) request.getAttribute("fileList");
        out.print(test);
    %>
    图片: <img src="<%=test%>" alt="">
</pre>
    <%--<%String s=application.getAttribute("fileList").toString();%>--%>
    <%--<img src="<%=s%>">--%>

    <%--<%out.println(<%for(var i=0;i<9;i++) {%>--%>
    <%--<img src="<%=s%>">--%>
    <%--<%}%>}%>--%>

    <%--<%for() {%>--%>
    <%--<img src="<%=s%>">--%>
    <%--<%}%>--%>


</p>
<%--<img alt="image" src="capture/seekExperts">--%>
<%--<input type="button" value="查看" onclick="getPicFileList()">--%>


</body>
</html>