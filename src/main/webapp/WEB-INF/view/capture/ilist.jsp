
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<script type="text/javascript" src="${basePath}/resources/uploadify/jquery.uploadify.min.js"></script>--%>
<%--<link rel="stylesheet" type="text/css" href="${basePath}/resources/uploadify/uploadify.css" />--%>
<%--<script type="text/javascript" src="${basePath}/resources/js/jquery-1.8.2.min.js"></script>--%>
<html>
<head>
    <title>抓拍图像</title>

</head>
<body>
捕捉图像的抓拍
<%--< img src=”ftp://用户名:密码@FTP地址:路径/”>--%>
<div>
    <img src=”ftp://sdzdz:hlxx@2017@218.28.166.165:19999/zdzjpg/”>
</div>


<%--<c:forEach var="me" items="${fileNameMap}">--%>
<%--<c:url value="/uploads/" var="downurl">--%>
<%--<c:param name="filename" value="${me.key}"></c:param>--%>
<%--</c:url>--%>
<%--${me.value}<a href="${downurl}">下载</a>--%>
<%--<br/>--%>
<%--</c:forEach>--%>

</body>
</html>