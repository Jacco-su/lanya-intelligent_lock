<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>

<script type="text/javascript">

</script>


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
                <td><input name="keyssCode" id="keyssCode" value="" class="easyui-validatebox" required="true"/></td>
            </tr>

            <tr>
                <td>自定义名称:</td>
                <td>
                    <input name="keyssName" class="easyui-validatebox" value="" required="true"/>
                </td>
            </tr>
            <tr>
                <td>钥匙MAC:</td>
                <td>
                    <input name="keyssMAC" value="点击获取" class="easyui-validatebox"
                           onclick="$('#selectArea').window('open');"
                           required="true"/>
                </td>
            </tr>

            <tr>
                <td width="100">领用人:</td>
                <td>
                    <input type="text" name="userName" id="userName" value="" readonly/> <a class="easyui-linkbutton"
                                                                                            onclick="$('#selectUser').window('open');">选择</a>
                    <input type="hidden" name="userid" id="usercode" value=""/>
                    <%--<select name="userId" name="userId" style="width:200px;" class="easyui-validatebox" required="true">--%>
                    <%--<option value="">---请选择---</option>--%>
                    <%--<c:forEach items="${userList}" var="user" varStatus="s">--%>
                    <%--<option value="${user.id}"--%>
                    <%--<c:if test="${user.id eq seuser.id}">selected</c:if>>${user.name}</option>--%>
                    <%--</c:forEach>--%>
                    <%--</select>--%>
                    <%--<a class="easyui-linkbutton" icon="icon-ok" onclick="search();">查询</a>--%>
                </td>

            </tr>
        </table>
    </form>
</div>

<div>
    <table>
        <tr>
            <td></td>
        </tr>

    </table>
</div>