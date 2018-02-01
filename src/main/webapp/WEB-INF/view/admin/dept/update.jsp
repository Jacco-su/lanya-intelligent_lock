<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma", "No-Cache");
	response.setHeader("Cache-Control", "No-Cache");
	response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div>
<form name="editForm" id="editForm" action="${basePath}/dept/update" method="post">
<table class="mytable" align="center">
	<tr>
        <td>区域名称:</td>
        <td><input type="hidden" name="id" value="${dept.id}"/>
            <input type="hidden" name="parentId" value="${dept.parentId}"/>
            <input name="name" value="${dept.name }" class="easyui-validatebox" required="true"/></td>
	</tr>
	<tr>
        <%--<td>所属区域:</td>--%>
        <%--<td>--%>
        <%--<select name="qgorgId" name="qgorgId" style="width:200px;" class="easyui-validatebox" required="true">--%>
        <%--<c:forEach items="${qgorgList}" var="qgorg" varStatus="s">--%>
        <%--<option value="${qgorg.id}" <c:if test="${qgorg.id eq dept.qgorgId}">selected</c:if>>${qgorg.name}</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
        <%--</td>--%>
	</tr>
	<tr>
		<td>排序:</td>
		<td><input name="orderId" value="${dept.orderId}" class="easyui-validatebox" required="true"/>
		<input type="hidden" name="treeShow" value="1"/>
		</td>
	</tr>

    <tr>
        <td>
            区域编号:
        </td>
        <td>
            <input value="${dept.id}" disabled="true "/>
        </td>
    </tr>
</table>
</form>
</div>