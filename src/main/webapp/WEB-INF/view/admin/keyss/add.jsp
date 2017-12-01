<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>


<style>


    input:focus {
        color: rgba(7, 7, 7, 0.99);
    }

    input:link {
        color: rgba(80, 76, 78, 0.8);
    }
</style>

<div>
    <form name="addForm" id="addForm" action="${basePath}/keyss/add" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>钥匙编号:</td>
                <td><input name="name" value="" class="easyui-validatebox" required="true"/></td>
            </tr>

            <tr>
                <td>自定义名称:</td>
                <td>
                    <input name="cdate" class="easyui-validatebox" value="" required="true"/>
                </td>
            </tr>
            <tr>
                <td>钥匙MAC:</td>
                <td>
                    <input name="cip" value="点击获取" class="easyui-validatebox" onclick="$('#selectArea').window('open');"
                           required="true"/>
                </td>
            </tr>

            <tr>
                <td width="100">领用人:</td>
                <td>
                    <%--<input type="text" name="name" id="username" value="" readonly/><a class="easyui-linkbutton"--%>
                    <%--onclick="$('#selectArea').window('open');">选择</a>--%>
                    <%--<input type="hidden" name="userid" id="usercode" value=""/>--%>
                    <select name="userId" name="userId" style="width:200px;" class="easyui-validatebox" required="true">
                        <option value="">---请选择---</option>
                        <c:forEach items="${userList}" var="user" varStatus="s">
                            <option value="${user.id}"
                                    <c:if test="${user.id eq seuser.id}">selected</c:if>>${user.name}</option>
                        </c:forEach>
                    </select>

                </td>
                <td>
                    <select name="qgdisId" name="qgdisId" style="width:200px;" class="easyui-validatebox"
                            required="true">
                        <option value="">---请选择---</option>
                        <c:forEach items="${qgdisList}" var="qgdis" varStatus="s">
                            <option value="${qgdis.id}"
                                    <c:if test="${qgdis.id eq seqgdis.id}">selected</c:if>>${qgdis.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>