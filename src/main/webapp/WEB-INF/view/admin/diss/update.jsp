<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div>
    <form name="editForm" id="editForm" action="${basePath}/disa/update" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>配电房名称:</td>
                <td>
                    <input  name="id" type="hidden" value="${qgdis.id}"/>
                    <input  name="dept.id" type="hidden" value="${qgdis.dept.id}"/>
                    <input name="name" class="easyui-validatebox" width="280px" value="${qgdis.name}" required="true"/>
                    </td>
            </tr>
            <tr>
                <td>所在地址:</td>
                <td>
                    <textarea name="address" class="easyui-validatebox" id="address" cols="30" rows="10"required="true">${qgdis.address}</textarea>
                </td>
            </tr>
        </table>
    </form>
</div>