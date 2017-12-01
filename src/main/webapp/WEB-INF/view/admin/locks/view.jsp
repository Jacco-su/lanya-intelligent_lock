<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div>
    <table class="mytable" align="center">
        <tr>
            <td>智能锁编号:</td>
            <td>${lock.id}</td>
        </tr>
        <tr>
            <td>识别码:</td>
            <td>
                ${lock.locksCode}
            </td>
        </tr>
        <tr>
            <td width="100">安装地点:</td>
            <td>
                ${lock.disName} 配电房
            </td>
        </tr>
        <tr>
            <td width="100">安装时间:</td>
            <td>
                ${lock.lockDate}
            </td>
        </tr>
        <tr>
            <td>排序:</td>
            <td>${lock.sortorder}
            </td>
        </tr>
    </table>
</div>