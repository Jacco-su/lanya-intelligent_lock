<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<div>
    <form name="addForm" id="addForm" action="${basePath}/authorization/onLine" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>
                    获取钥匙MAC:
                </td>
                <td>
                    <input type=""> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    授权期限:
                </td>
                <td>
                    <input type="datetime"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    授权使用人:
                </td>
                <td>
                    <input type=""> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    锁具范围:
                </td>
                <td>
                    <input type="text"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    操作人:
                </td>
                <td>
                    <input type="text"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    工作票:
                </td>
                <td>
                    <input type="file" value="ss"> <br/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" value="确认授权">
                </td>
                <td>
                    <input type="reset" value="重置"><br/>
                </td>
            </tr>


        </table>
    </form>
</div>