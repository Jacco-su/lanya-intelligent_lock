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
    <form name="editForm" id="editForm" action="${basePath}/collector/update" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>采集器ID:</td>
                <td><input type="hidden" name="cid" value="${collector.id}"/>
                    <input name="name" value="${collector.cid}" class="easyui-validatebox" required="true"/></td>
            </tr>

            <tr>
                <td width="100">所属配电房</td>
                <td>
                    <input type="text" name="disame" id="areaname" value="${collector.dis}" readonly/><a
                        class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
                    <input type="hidden" name="discode" id="discode" value="${collector.dis}"/>
                </td>
            </tr>
            <tr>
                <td>IP地址:</td>
                <td>
                    <input name="cip" value="${collector.cip}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
            <tr>
                <td>日期:</td>
                <td><input name="cdate" value="${collector.cdate}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>