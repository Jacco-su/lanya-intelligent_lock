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
            <td>授权名称:</td>
            <td>
                ${authLog.authName}
            </td>
        </tr>
        <tr>
            <td>授权人员:</td>
            <td>
                ${authLog.user.username}
            </td>
        </tr>
        <tr>
            <td>授权钥匙:</td>
            <td>
                ${authLog.authKeys}
            </td>
        </tr>
        <tr>
            <td>授权锁具:</td>
            <td>
                ${authLog.authLocks}
            </td>
        </tr>
        <tr>
            <td width="100">授权开始时间:</td>
            <td>
                ${authLog.authStartTime}
            </td>
        </tr>
        <tr>
            <td>授权结束时间:</td>
            <td>
                ${authLog.authEndTime}
            </td>
        </tr>
        <tr>
            <td>已授权门锁数:</td>
            <td>
                ${authLog.authIndex}
            </td>
        </tr>
    </table>
</div>