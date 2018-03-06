<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div>
    <form name="editForm" id="editForm" action="${basePath}/orga/update" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>区域名称:</td>
                <td><input type="hidden" name="id" value="${orga.id}"/>
                    <input name="name" value="${orga.name }" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>区域地址:</td>
                <td>
                    <input name="address" value="${orga.address}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td width="100">管辖地区</td>
                <td>
                    <input type="text" name="areaname" id="areaname" value="${orga.areaname}" readonly/>
                    <a class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
                    <input type="hidden" name="areacode" id="areacode" value="${orga.areacode}"/>
                </td>
            </tr>
            <tr>
                <td>排序:</td>
                <td><input name="sortorder" value="${orga.sortorder}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>