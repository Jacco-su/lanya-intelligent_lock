<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
        color: rgba(90, 90, 90, 0.99);
    }

    input:link {
        color: rgba(233, 228, 230, 0.8);
    }

    input {
        width: 200px;
    }
</style>
<div>
    <form name="addForm" id="addForm" action="${basePath}/collector/add" method="post">
        <table class="mytable" align="center">

            <tr>
                <td>采集器ID:</td>
                <td>
                    <input name="ccode" id="ccode" class="easyui-validatebox" required="true" onblur="ce()"
                           validType="ccode"/>

                </td>
            </tr>
            <tr>
                <td>IP地址:</td>
                <td>
                    <input name="cip" value="" class="easyui-validatebox" required="true"/>
                    <%--<a class="easyui-linkbutton"--%>
                    <%--onclick="$('#').window('open');">获取</a>--%>

                </td>
            </tr>

            <tr>
                <td width="100">所属站点:</td>
                <td>
                    <select id="dissname" name="dis.id" style="width:200px;"></select>

                    <%--<input type="hidden" name="areacode" id="areacode" value=""/>--%>
                </td>
            </tr>
        </table>
    </form>
</div>

<script>
    $(function () {
        var data = '${qgdisList}';
        data = JSON.parse(data);
        $('#dissname').empty();
        for (var i = 0; i < data.length; i++) {
            $('#dissname').append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
        }
    });

    function ceid(value) {
        message = "";

        // if (value.length == 8 && value==!(/^[\u0391-\uFFE5]+$/.test(value)))


        return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{8})$|\s/
            .test(value));
        message = 'ID由字母和数字组成，至少8位';
    };

    function ceID(value) {
        //
        if (isNaN(value)) {
            obj.value = '';

        }

    };

    function ce() {
        var p = $("#ccode").val();
        if (!p.match(/\d{8}$/)) {
//            alert("请输入8位数字！");
            $.messager.alert('提示', '请输入8位数字！', 'warning');
        }
        if (p.length > 8) {
            $.messager.alert('提示', '输入超过8位！', 'warning');
        }


    };


</script>