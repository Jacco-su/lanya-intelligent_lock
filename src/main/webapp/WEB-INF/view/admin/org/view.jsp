<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma", "No-Cache");
	response.setHeader("Cache-Control", "No-Cache");
	response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div>
<table class="mytable" align="center">
	<tr>
		<td>地区名称:</td>
		<td>${orga.name}</td>
	</tr>
	<tr>
		<td>地址:</td>
		<td>
		${orga.address}
		</td>
	</tr>	
	<tr>
		<td width="100">管辖区域:</td>
		<td>
				${orga.areaname}	
		</td>
	</tr>
	<tr>
		<td>排序:</td>
		<td>${orga.sortorder}
		</td>
	</tr>
</table>
</div>