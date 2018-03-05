<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
         <%--pageEncoding="UTF-8" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%--<%--%>
    <%--response.setHeader("Pragma", "No-Cache");--%>
    <%--response.setHeader("Cache-Control", "No-Cache");--%>
    <%--response.setDateHeader("Expires", 0);--%>
<%--%>--%>

<%--<style>--%>

    <%--input:focus {--%>
        <%--color: rgba(7, 7, 7, 0.99);--%>
    <%--}--%>

    <%--input:link {--%>
        <%--color: rgba(80, 76, 78, 0.8);--%>
    <%--}--%>
<%--</style>--%>

<%--&lt;%&ndash;<script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"&ndash;%&gt;--%>
<%--&lt;%&ndash;charset="UTF-8"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="text/javascript" src="${basePath}/js/calendar/WdatePicker.js"></script>&ndash;%&gt;--%>


<%--<script type="text/javascript">--%>
    <%--var basePath = "${basePath}";--%>
    <%--$(function () {--%>
            <%--&lt;%&ndash;var d='${dissList}';&ndash;%&gt;--%>
            <%--refresh();--%>

            <%--//获取站点--%>

            <%--function refresh() {--%>
<%--//            $(function () {--%>
<%--//--%>
<%--//                d=JSON.parse(data);--%>
<%--//                $('#disa').empty();--%>
<%--//                for (var i=0;i<data.length;i++){--%>
<%--//                    $('#disa').append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");--%>
<%--//                }--%>
<%--//--%>
<%--//--%>
<%--//--%>
<%--////            })--%>

<%--//            var data={--%>
<%--//                "disaId":""--%>
<%--//            };--%>
<%--//            $.post(basePath+"/authorization/distribution",data,function(data){--%>
<%--//--%>
<%--//                var d=JSON.parse(data);--%>
<%--//                $('#disa').empty();--%>
<%--//                var disaData = []; //创建数组--%>
<%--//                for(var i=0;i<d.length;i++){--%>
<%--//                    disaData.push({--%>
<%--//                        "id": d[i].id,--%>
<%--//                        "text": d[i].name--%>
<%--//                    });--%>
<%--//                }--%>
<%--//                if( d[0]!=null) {--%>
<%--//                    $("#disa").combobox("clear")//下拉框加载数据,设置默认值为--%>
<%--//                        .combobox("loadData", disaData).combobox("setValue", d[0].id);--%>
<%--//                }--%>
<%--//            });--%>

                <%--var data = {--%>
                    <%--"disaId": ""--%>
                <%--};--%>
                <%--$.post(basePath + "/authorization/distribution", data, function (data) {--%>
                    <%--&lt;%&ndash;var d='${dissList}';&ndash;%&gt;--%>
                    <%--var d = JSON.parse(data);--%>
                    <%--$('#disa').empty();--%>
                    <%--var disaData = []; //创建数组--%>
                    <%--for (var i = 0; i < d.length; i++) {--%>
                        <%--disaData.push({--%>
                            <%--"id": d[i].id,--%>
                            <%--"text": d[i].name--%>
                        <%--});--%>
                    <%--}--%>
                    <%--if (d[0] != null) {--%>
                        <%--$("#disa").combobox("clear")//下拉框加载数据,设置默认值为--%>
                            <%--.combobox("loadData", disaData).combobox("setValue", d[0].id);--%>
                    <%--}--%>
                <%--});--%>

                <%--//获取采集器--%>
                <%--$('#disa').combobox({--%>
                    <%--onSelect: function (row) {--%>
                        <%--if (row != null) {--%>
                            <%--var data = {--%>
                                <%--"disaId": row.id--%>
                            <%--};--%>
                            <%--$('#collectore').empty();--%>
                            <%--$.post(basePath + "/authorization/disa/collector", data, function (data) {--%>
                                <%--var d = JSON.parse(data);--%>
                                <%--$('#collector').empty();--%>
                                <%--var collectorData = []; //创建数组--%>
                                <%--for (var i = 0; i < d.length; i++) {--%>
                                    <%--collectorData.push({--%>
                                        <%--"id": d[i].id,--%>
                                        <%--"text": d[i].ccode--%>
                                    <%--});--%>
                                <%--}--%>
                                <%--if (d[0] != null) {--%>
                                    <%--$("#collector").combobox("clear")//下拉框加载数据,设置默认值为--%>
                                        <%--.combobox("loadData", collectorData).combobox("setValue", d[0].id);--%>
                                <%--}--%>
                            <%--});--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>
                <%--//获取控制器--%>
                <%--$('#collector').combobox({--%>
                    <%--onSelect: function (row) {--%>
                        <%--if (row != null) {--%>
                            <%--var data = {--%>
                                <%--"collectorId": row.id--%>
                            <%--};--%>
                            <%--$.post(basePath + "/authorization/collector/collectore", data, function (data) {--%>
                                <%--var d = JSON.parse(data);--%>
                                <%--$('#collectore').empty();--%>
                                <%--var collectorData = []; //创建数组--%>
                                <%--for (var i = 0; i < d.length; i++) {--%>
                                    <%--collectorData.push({--%>
                                        <%--"id": d[i].id,--%>
                                        <%--"text": d[i].ceMAC--%>
                                    <%--});--%>
                                <%--}--%>
                                <%--//console.log(collectorData);--%>
                                <%--if (d[0] != null) {--%>
                                    <%--$("#collectore").combobox("clear")//下拉框加载数据,设置默认值为--%>
                                        <%--.combobox("loadData", collectorData).combobox("setValue", d[0].id);--%>
                                <%--}--%>
                            <%--});--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>

            <%--}--%>

        <%--}--%>
    <%--);--%>

    <%--//初始化 获取门锁信息--%>
    <%--function getLock(t) {--%>
        <%--var key = $('#collector').combobox('getText') + ","--%>
            <%--+ t + ","--%>
            <%--+ $('#collectore').combobox('getText') + ","--%>
            <%--+ $('#keys').combobox('getText') + ","--%>
            <%--+ $('#locks').combobox('getText') + ","--%>
            <%--+ $('#startDate').val() + ","--%>
            <%--+ $('#endDate').val() + ","--%>
            <%--+ $('#users').combobox('getValue');--%>
        <%--var data = {--%>
            <%--"key": key--%>
        <%--};--%>
        <%--$.ajax({--%>
            <%--type: "post",--%>
            <%--url: basePath + "/redis/get",--%>
            <%--cache: false,--%>
            <%--async: false,--%>
            <%--data: data,--%>
            <%--dataType: "json",--%>
            <%--success: function (data) {--%>
                <%--if (data.result == "1") {--%>
                    <%--if (t == 2) {--%>
                        <%--alert(data.message);--%>
                    <%--} else {--%>
                        <%--$("#locks").empty();--%>
                        <%--var collectorData = []; //创建数组--%>
                        <%--var lockNum = data.message.split(":")[1];--%>
                        <%--collectorData.push({--%>
                            <%--"id": lockNum,--%>
                            <%--"text": lockNum--%>
                        <%--});--%>
                        <%--console.log(data.message.split(":")[1]);--%>
                        <%--console.log(collectorData);--%>
                        <%--$("#locks").combobox("clear")//下拉框加载数据,设置默认值为--%>
                            <%--.combobox("loadData", collectorData).combobox("setValue", lockNum);--%>
                    <%--}--%>
                <%--} else {--%>
                    <%--if (t == 2) {--%>
                        <%--alert("初始化门锁失败!");--%>
                    <%--} else {--%>
                        <%--alert("读取门锁信息失败!");--%>
                    <%--}--%>
                <%--}--%>
            <%--}--%>

        <%--});--%>
    <%--}--%>
<%--</script>--%>

<%--<div>--%>
    <%--<form name="addForm" id="addForm" action="${basePath}/locks/add" method="post">--%>
        <%--<table class="mytable" align="center">--%>


            <%--<tr>--%>
                <%--<td>站点:</td>--%>
                <%--<td colspan="2">--%>
                    <%--<select class="easyui-combobox" name="qgdis.id" id="disa" style="width: 180px;"--%>
                            <%--data-options="editable:false,valueField:'id', textField:'text'">--%>
                        <%--<option value="0">---请选择---</option>--%>
                    <%--</select>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>选择采集器:</td>--%>
                <%--<td colspan="2">--%>
                    <%--<select class="easyui-combobox" id="collector" name="collector" style="width: 180px;"--%>
                            <%--data-options="editable:false,valueField:'id', textField:'text'">--%>
                        <%--<option value="0">---请选择---</option>--%>
                    <%--</select>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>控制器:</td>--%>
                <%--<td colspan="2">--%>
                    <%--<select class="easyui-combobox" name="collectore" id="collectore" style="width: 200px;"--%>
                            <%--data-options="editable:false,valueField:'id', textField:'text'">--%>
                        <%--<option value="0">---请选择---</option>--%>
                    <%--</select>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="100">门锁编号:</td>--%>
                <%--<td colspan="2">--%>
                    <%--<input id="lockNum" name="lockNum" style="width: 200px;"/>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="100">门锁识别码:</td>--%>
                <%--<td colspan="2">--%>
                    <%--<input id="lockCode" name="lockCode" style="width: 200px;"/>--%>
                    <%--<a class="easyui-linkbutton"--%>
                       <%--onclick="getLock(2)">初始化</a>--%>
                    <%--<a class="easyui-linkbutton"--%>
                       <%--onclick="getLock(1)">获取</a>--%>
            <%--</tr>--%>

            <%--<tr>--%>
                <%--<td width="100">添加时间:</td>--%>
                <%--<td colspan="2">--%>
                    <%--<input id="lockDate" name="lockDate" class="easyui-validatebox" required="true" value=""/>--%>
                    <%--<img onclick="WdatePicker({el:'lockDate'})" src="${basePath}/js/calendar/skin/datePicker.gif"--%>
                         <%--width="16" height="22" align="absmiddle">--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="100">详细地址:</td>--%>
                <%--<td colspan="2">--%>
                    <%--<textarea name="address" class="easyui-validatebox" id="address" cols="30" rows="3"--%>
                              <%--required="true"></textarea>--%>
            <%--</tr>--%>

        <%--</table>--%>
    <%--</form>--%>
<%--</div>--%>
<%--<script>--%>
    <%--&lt;%&ndash;$(function () {&ndash;%&gt;--%>
    <%--&lt;%&ndash;var data='${dissList}';&ndash;%&gt;--%>
    <%--&lt;%&ndash;data=JSON.parse(data);&ndash;%&gt;--%>
    <%--&lt;%&ndash;$('#disa').empty();&ndash;%&gt;--%>
    <%--&lt;%&ndash;for (var i=0;i<data.length;i++){&ndash;%&gt;--%>
    <%--&lt;%&ndash;$('#disa').append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");&ndash;%&gt;--%>
    <%--&lt;%&ndash;}&ndash;%&gt;--%>
    <%--&lt;%&ndash;})&ndash;%&gt;--%>
<%--</script>--%>