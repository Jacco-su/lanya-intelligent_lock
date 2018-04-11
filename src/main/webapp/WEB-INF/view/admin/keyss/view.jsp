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
            <td>钥匙编号:</td>
            <td>${keyss.keyssCode}</td>
        </tr>
        <tr>
            <td>自定义名称:</td>
            <td>
                ${keyss.keyssName}
            </td>
        </tr>
        <tr>
            <td>钥匙MAC:</td>
            <td>
                ${keyss.keyssMAC}
            </td>
        </tr>
        <%--<tr>--%>
        <%--<td width="100">领用人:</td>--%>
        <%--<td>--%>
        <%--${keyss.userName}--%>
        <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td>领用时间:</td>
            <td>${keyss.keyssDate}
            </td>
        </tr>
        <tr>
            <td>授权锁具:</td>
            <td>${authLog.authLocks}</td>
        </tr>
        <tr>
            <td>授权开始时间:</td>
            <td>${authLog.authStartTime}</td>
        </tr>
        <tr>
            <td>授权开始时间:</td>
            <td>${authLog.authEndTime}</td>
        </tr>
    </table>
</div>