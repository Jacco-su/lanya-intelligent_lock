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
    <form name="editForm" id="editForm" action="${basePath}/keyss/prUpdate" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>钥匙编号:</td>
                <td><input type="hidden" name="id" value="${keyss.id}"/>
                    <input name="name" value="${keyss.keyssCode}" class="easyui-validatebox" required="true"/></td>
            </tr>

            <tr>
                <td>自定义名称:</td>
                <td>
                    <input name="cip" value="${keyss.keyssName}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>钥匙MAC:</td>
                <td><input name="cdate" value="${keyss.keyssMAC}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>

            <tr>
                <td width="100">领用人：</td>
                <td>
                    <input type="text" name="disame" id="areaname" value="${keyss.userName}" readonly/><a
                        class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
                    <input type="hidden" name="discode" id="discode" value="${keyss.id}"/>
                </td>
            </tr>
        </table>
    </form>
</div>