<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>


    $(function () {
        $('#userTree').tree({
            checkbox: false,
            url: basePath+'/dept/getChildren',
            onBeforeExpand:function(node,param){
                $('#userTree').tree('options').url = basePath+"/dept/getChildren?parentId=" + node.id;
            },
            onClick:function(node){
                getUsers(node.id);
            }
        });
        function getUsers(obj) {
            var data={
                "disaId":obj
            };
            //获取站点
            $.post(basePath+"/authorization/distribution",data,function(data){
                var d=JSON.parse(data);
                $('#disa').empty();
                $('#disa').append("<option value='" + 0 + "'>" + "--请选择--" + "</option>");
                for(var i=0;i<d.length;i++){
                    $('#disa').append("<option value='" + d[i].id + "'>" + d[i].name + "</option>");
                    /*if(){
                        $('#usersname').append("<option selected value='" + d[i].id + "'>" + d[i].username + "</option>");
                    }else{
                        $('#usersname').append("<option value='" + d[i].id + "'>" + d[i].username + "</option>");
                    }*/

                }
            });

        }
        $("#disa").change(function(){
            var options=$("#disa option:selected");
            var data={
                "disaId":options.val()
            };
            //获取采集器
            $.post(basePath+"/authorization/disa/collector",data,function(data){
                var d=JSON.parse(data);
                $('#collector').empty();
                $('#collector').append("<option value='" + 0 + "'>" + "--请选择--" + "</option>");
                for(var i=0;i<d.length;i++){
                    $('#collector').append("<option value='" + d[i].ccode+','+ d[i].id+ "'>" + d[i].cip + "</option>");


                }
            });
        });
        $("#collector").change(function(){
            var options=$("#collector option:selected");
            var macandID = options.val();
            var arr = macandID.split(',');
            var data={
                "collectorId":arr[1]
            };
            //获取控制器
            $.post(basePath+"/authorization/collector/collectore",data,function(data){
                var d=JSON.parse(data);
                $('#collectore').empty();
                $('#collectore').append("<option value='" + 0 + "'>" + "--请选择--" + "</option>");
                for(var i=0;i<d.length;i++){
                    $('#collectore').append("<option value='" + d[i].ceMAC + "'>" + d[i].cename + "</option>");


                }
            });
        });
        $("#butt").click(function(){
            var options=$("#collector option:selected");
            var optionse=$("#collectore option:selected");
            var coller = options.val();
            var collere = optionse.val();
            var arr = coller.split(',');
            console.log(arr[0]+'--'+collere)
            var key = arr[0] + ","
                + "14" + ","
                + collere + ",";
            var data = {
                "key": key
            };
            $.ajax({
                type: "post",
                url: basePath + "/redis/get",
                cache: false,
                async: false,
                data: data,
                dataType: "json",
                success: function (data) {
                    if (data.result == "1") {
                        alert(data.message);
                    } else {
                        alert("读取日志信息失败!");
                    }
                }

            });
        })
    });
</script>
<%--/**   修改*/--%>
<div>
    <%--<form name="editForm" id="editForm" action="${basePath}/keyss/update" method="post">--%>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" height="530">
            <tr>
                <td width="24%" valign="top"
                    style="border: 1px solid #99bbe8; border-right: 0;">
                    <div class="panel-header" style="border-left: 0; border-right: 0;">区域</div>
                    <ul id="userTree" style="margin-top: 10px;"></ul>
                </td>
                <td valign="top" style="border: 1px solid #99bbe8;">
                    <table class="mytable" align="center">
                        <tr>
                            <td>站点:</td>
                            <td colspan="3">
                                <select name="disa" id="disa" style="width: 180px;">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>采集器:</td>
                            <td colspan="3">
                                <select name="collector" id="collector" style="width: 180px;">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>控制器:</td>
                            <td colspan="3">
                                <select name="collectore" id="collectore" style="width: 180px;">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <%--<td colspan="3"><button id="butt" onclick="getLog()">刷新</button></td>--%>
                            <td colspan="3"><button id="butt" class="easyui-linkbutton" >刷新</button></td>

                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    <%--</form>--%>
</div>
