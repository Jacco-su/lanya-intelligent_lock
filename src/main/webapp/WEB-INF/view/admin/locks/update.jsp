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
    <form name="editForm" id="editForm" action="${basePath}/locks/prUpdate" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>智能锁编号:</td>
                <td><input type="hidden" name="id" value="${locks.id}"/>
                    <input name="lockNum" value="${locks.lockNum}" class="easyui-validatebox" required="true"/></td>

            </tr>
            <tr>
                <td>识别码:</td>
                <td>
                    <input name="locksCode" value="${locks.lockCode}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>


            <tr>
                <td width="100">所属配电房:</td>
                <td>
                    <input type="text" name="disame" id="areaname" value="${locks.qgdis.id}" readonly/><a
                        class="easyui-linkbutton" onclick="$('#selectArea').window('open');">选择</a>
                    <input type="hidden" name="discode" id="discode" value="${locks.qgdis}"/>

                </td>
            </tr>

            <tr>
                <td>日期:</td>
                <td><input name="cdate" value="${locks.lockDate}" class="easyui-validatebox" required="true"/>
                </td>
            </tr>
        </table>
    </form>
</div>