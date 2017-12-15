<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	response.setHeader("Pragma", "No-Cache");
	response.setHeader("Cache-Control", "No-Cache");
	response.setDateHeader("Expires", 0);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>区域添加</title>
</head>
<body>
<form name="addForm" id="addForm" action="add" method="post">
<table class="mytable" align="center">
	<tr>
        <td>区域名称:</td>
		<td><input type="hidden" name="parentId"  value="${parentId }"/><input name="name"  class="easyui-validatebox" required="true"/></td>
	</tr>
    <%--<tr>--%>
    <%--<td>所属区域:</td>--%>
    <%--<td>--%>
    <%--<select name="qgorgId" name="qgorgId" style="width:200px;" class="easyui-validatebox" required="true">--%>
    <%--<option value="">---请选择---</option>--%>
    <%--<c:forEach items="${qgorgList}" var="qgorg" varStatus="s">--%>
    <%--<option value="${qgorg.id}"--%>
    <%--<c:if test="${qgorg.id eq seqgorg.id}">selected</c:if>>${qgorg.name}</option>--%>
    <%--</c:forEach>--%>
    <%--</select>--%>
    <%--</td>--%>
    <%--</tr>	--%>
	<tr>
		<td>排序:</td>
		<td>
		<input name="orderId"  class="easyui-validatebox" required="true"/>
		<input type="hidden" name="treeShow" value="1"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
