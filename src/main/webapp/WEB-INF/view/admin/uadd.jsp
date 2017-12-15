<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div>
    <form name="addForm" id="addForm" action="${basePath}/user/uadd" method="post">
        <table class="mytable" align="center">


            <tr>
                <td>姓名:</td>
                <td><input size="40" class="easyui-validatebox" name="username" required="true" validType="CHS"/></td>
            </tr>


            <%--<tr>
                <td>绩效考核:</td>
                <td>
                                <select name="haskh" id="haskh">
                                            <option value="1" selected>参与</option>
                                            <option value="0" >不参与</option>
                                </select>
                </td>
            </tr>--%>
            <tr>
                <td>手机:</td>
                <td><input size="40" class="easyui-validatebox" name="phone" value="" validType="mobile"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input size="40" class="easyui-validatebox" name="email" validType="email"></td>
            </tr>
            <tr>
                <td>排序序号:</td>
                <td>

                    <select id="orderId" name="orderId" style="width:100px;">
                        <c:forEach var="x" begin="1" end="50" step="1">
                            <option value="${x}">${x}</option>
                        </c:forEach>
                    </select>


                </td>
            </tr>
            <tr>
                <td>角色:</td>
                <td>
                    <input name="roIdList" type="hidden"/>
                    <c:forEach items="${roles}" var="role" varStatus="s">
                        <span><input name="roIdList" type="checkbox" value="${role.roId}"/>${role.name }</span>
                        <c:if test="${(s.index+1)%3==0}"><br/></c:if>
                    </c:forEach>
                </td>
            </tr>
            <%--<tr>--%>
            <%--<td>负责区域:</td>--%>
            <%--<td>--%>
            <%--<select id="deptId" name="deptId" style="width:100px;">--%>
            <%--&lt;%&ndash;/<div class="easyui-accordion" style="width: 500px;"  border=false animate=false>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:forEach items="${deptList}" var="dept" varStatus="s">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<span><input name="deptIdList" type="checkbox" value="${dept.id}"/>${dept.name }</span>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:if test="${(s.index+1)%5==0}"><br/></c:if>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%----%>
            <%--</select>--%>
            <%--</td>--%>
            <%--</tr>--%>
        </table>
    </form>
</div>