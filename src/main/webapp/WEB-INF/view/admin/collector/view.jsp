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
            <td>采集器编号:</td>
            <td>${collectora.id}</td>
        </tr>
        <tr>
            <td>所属配电房:</td>
            <td>
                ${collectora.qgdisname}
            </td>
        </tr>
        <tr>
            <td width="100">IP地址:</td>
            <td>
                ${collectora.cip}
            </td>
        </tr>
        <tr>
            <td>排序:</td>
            <td>${collectora.sortorder}
            </td>
        </tr>
    </table>
</div>