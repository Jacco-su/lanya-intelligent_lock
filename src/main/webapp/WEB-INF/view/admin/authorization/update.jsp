<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    response.setHeader("Pragma", "No-Cache");
    response.setHeader("Cache-Control", "No-Cache");
    response.setDateHeader("Expires", 0);
%>


<meta charset="UTF-8">
<title>Basic DateTimeBox - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css" href="${basePath}/js/jquery-easyui-1.5.3/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/js/jquery-easyui-1.5.3/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/js/jquery-easyui-1.5.3/demo/demo.css">


<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.5.3/jquery.easyui.mobile.js"></script>

<script>
    $('#dt').datetimebox({
        value: '',
        required: true,
        showSeconds: true

    });
    $(function () {
        $('#dt').timespinner({
            value: '00:00',
            min: '00:00',
            max: '23:59',
            editable: false,
            width: '80px',
            height: '100px',
        });
    });

    function ww3(date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var str = y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d) + ':' + (h < 10 ? ('0' + h) : h;
        return str;
    }

    function w3(s) {
        if (!s) return new Date();
        var y = s.substring(0, 4);
        var m = s.substring(5, 7);
        var d = s.substring(8, 10);
        var h = s.substring(11, 14);
        var min = s.substring(15, 17);
        var sec = s.substring(18, 20);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d) && !isNaN(h) && !isNaN(min) && !isNaN(sec)) {
            return new Date(y, m - 1, d, h, min, sec);
        } else {
            return new Date();
        }
    }

    //    $(function()
    //    {
    //        var curr_time=new Date();
    //        var strDate=curr_time.getFullYear()+"-";
    //        strDate +=curr_time.getMonth()+1+"-";
    //        strDate +=curr_time.getDate()+"-";
    //        strDate +=" "+curr_time.getHours()+":";
    //        strDate +=curr_time.getMinutes()+":";
    //        strDate +=curr_time.getSeconds();
    //        $("#kssj").datetimebox("setValue",strDate);
    //        $("#jssj").datetimebox("setValue",strDate);
    //    });

</script>

<%--/**   修改*/--%>
<div>
    <form name="addForm" id="addForm" action="${basePath}/authorization/onLine" method="post">
        <table class="mytable" align="center">
            <tr>
                <td>
                    获取钥匙MAC:
                </td>
                <td>
                    <input type="" onclick="keyssMAC"> <br/>
                </td>
            </tr>
            <tr>
                <td rowspan="2">
                    授权期限:
                </td>
                <td>
                    <div>
                        <%--<input class="easyui-textbox,easyui-datetimebox" style="width:100%" data-options="--%>
                        <%--label: 'Standard TextBox:',--%>
                        <%--labelPosition: 'top',--%>
                        <%--prompt: 'Search...'--%>
                        <%--">--%>

                        <%--1:<input id="dt"  name="birthday">--%>
                        <%--<input class="easyui-datetimebox" name="birthday"--%>
                        <%--data-options="required:true,showSeconds:false" value="" style="width:200px">--%>

                        <%--开始日期:<input type="text" class="easyui-datetimebox" style="width:150px" id="kssj">--%>
                        <%--&nbsp; &nbsp;--%>
                        <%--结束日期:<input type="text" class="easyui-datetimebox" style="width:150px" id="jssj">--%>

                        <div style="margin:20px 0;">开始时间</div>
                        <input id="dt0" class="easyui-datetimebox,easyui-timespinner"
                               data-options="required:true,showSeconds:true ,onChange:onSelectT"
                               value="10/11/2012 2:3:56" style="width:200px">

            <tr>

                <td>
                    <div style="margin:20px 0;"></div>
                    <input class="eeasyui-datetimebox "
                           data-options="required:true,showSeconds:true,formatter:ww3,parser:w3"
                           value="10/11/2018 12:3:56" style="width:200px">
                </td>
            </tr>
            <%--<div style="margin:20px 0;"></div>--%>
            <%--<div class="panel panel-htop easyui-fluid" style="width: 400px; display: block;"><div title="" class="easyui-panel panel-body panel-body-noheader" style="padding: 30px 60px; width: 278px;">--%>
            <%--<div style="margin-bottom:20px">--%>
            <%--<label class="textbox-label textbox-label-top" style="width: 278px; text-align: left;" for="_easyui_textbox_input1">width: 100%</label><input class="easyui-datetimebox combo-f textbox-f datetimebox-f" style="width: 100%; display: none;" labelposition="top" label="width: 100%"><span class="textbox easyui-fluid combo datebox" style="width: 276px;"><span class="textbox-addon textbox-addon-right" style="top: 0px; right: 0px;"><a tabindex="-1" class="textbox-icon combo-arrow" style="width: 18px; height: 22px;" href="javascript:;" icon-index="0"></a></span><input tabindex="-32768" class="textbox-text validatebox-text textbox-prompt" id="_easyui_textbox_input1" style="margin: 0px 18px 0px 0px; width: 250px; height: 22px; line-height: 22px; padding-top: 0px; padding-bottom: 0px;" type="text" placeholder="" autocomplete="off"><input class="textbox-value" type="hidden" value=""></span>--%>
            <%--</div>--%>
            <%--<div style="margin-bottom:20px">--%>
            <%--<label class="textbox-label textbox-label-top" style="width: 222px; text-align: left;" for="_easyui_textbox_input3">width: 80%</label><input class="easyui-datetimebox combo-f textbox-f datetimebox-f" style="width: 80%; display: none;" labelposition="top" label="width: 80%"><span class="textbox easyui-fluid combo datebox" style="width: 220px;"><span class="textbox-addon textbox-addon-right" style="top: 0px; right: 0px;"><a tabindex="-1" class="textbox-icon combo-arrow" style="width: 18px; height: 22px;" href="javascript:;" icon-index="0"></a></span><input tabindex="-32768" class="textbox-text validatebox-text textbox-prompt" id="_easyui_textbox_input3" style="margin: 0px 18px 0px 0px; width: 194px; height: 22px; line-height: 22px; padding-top: 0px; padding-bottom: 0px;" type="text" placeholder="" autocomplete="off"><input class="textbox-value" type="hidden" value=""></span>--%>
            <%--</div>--%>
            <%--</div>--%>


            <%--</div>--%>


</div>
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
