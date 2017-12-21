<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>配电房添加</title>
</head>
<body>
<form name="addForm" id="addForm" action="add" method="post">
    <table class="mytable" align="center">
        <tr>
            <td>配电房名称:</td>
            <td>
                <input  name="dept.id" type="hidden" value="${deptId}"/>
                <input name="name" class="easyui-validatebox" width="280px" required="true"/></td>
        </tr>
        <tr>
            <td>所在地址:</td>
            <td>
                <textarea name="address" class="easyui-validatebox" id="address" cols="30" rows="10"required="true"></textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
