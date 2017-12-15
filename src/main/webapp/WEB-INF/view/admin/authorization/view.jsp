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
            <td>区域名称:</td>
            <td>
                ${authorizationa.name}
            </td>
        </tr>
        <tr>
            <td>区域地址:</td>
            <td>
                ${authorizationa.address}
            </td>
        </tr>
        <tr>
            <td width="100">管辖地区:</td>
            <td>
                ${authorizationa.areaname}
            </td>
        </tr>
        <tr>
            <td>排序:</td>
            <td>
                ${authorizationa.sortorder}
            </td>
        </tr>
    </table>
</div>