<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--/**   修改*/--%>
<div>
    <form name="editForm" id="editForm" action="${basePath}/locks/update" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>采集器编号:</td>
                <td><input type="hidden" name="id" value="${collectora.id}"/>
                    <input name="name" value="${collectora.name }" class="easyui-validatebox" required="true"/></td>
            </tr>

            <tr>
                <td width="100">所属配电房</td>
                <td>
                    <input type="text" name="disame" id="areaname" value="${collectora.areaname}" readonly/><a
                        class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
                    <input type="hidden" name="discode" id="discode" value="${collectora.areacode}"/>
                </td>
            </tr>
            <tr>
                <td>IP地址:</td>
                <td>
                    <input name="cip" value="${collectora.cip}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>日期:</td>
                <td><input name="cdate" value="${collectora.cdate}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>