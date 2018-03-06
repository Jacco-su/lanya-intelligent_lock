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
            <input type="hidden" name="areacode" value="4101"/>
            <input name="name" value="${dept.name }" class="easyui-validatebox" required="true"/></td>
	</tr>
	<%--<tr>
		<td>排序:</td>
		<td><input name="orderId" value="${dept.orderId}" class="easyui-validatebox" required="true"/>
		<input type="hidden" name="treeShow" value="1"/>
		</td>
	</tr>--%>

    <tr>
        <td>所属地区:</td>
        <td>
            <input name="areac" coords="" href="" alt="" value="${dept.areacode}"/>

            <input type="text" name="areaname" id="areaname" value="${qgorga.areaname}" readonly/>
            <a class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
            <input type="hidden" name="areacode" id="areacode" value="${qgorga.areacode}"/>
        </td>
    </tr>
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