<%--using DbService;--%>
<%--using System;--%>
<%--using System.Collections.Generic;--%>
<%--using System.Linq;--%>
<%--using System.Web;--%>
<%--using System.Web.Mvc;--%>
<%--using System.Web.Script.Serialization;--%>

<%--namespace MvcAppEF.Controllers--%>
<%--{--%>
<%--public class HomeController : Controller--%>
<%--{--%>
<%--public ActionResult Index9()--%>
<%--{--%>
<%--return View();--%>
<%--}--%>
<%--public ActionResult getData()--%>
<%--{--%>
<%--JavaScriptSerializer jsz = new JavaScriptSerializer();--%>
<%--salesEntities db = new salesEntities();--%>
<%--int countData = db.T_UserInfo.Count();--%>
<%--var list = db.T_UserInfo.ToList();--%>

<%--var x = new { total = countData, rows = list };--%>

<%--return Content(jsz.Serialize(x));--%>
<%--}--%>

<%--public ActionResult getUserInfo(int id)--%>
<%--{--%>
<%--JavaScriptSerializer jsz = new JavaScriptSerializer();--%>
<%--salesEntities db = new salesEntities();--%>
<%--int countData = db.T_UserInfo.Where(r => r.Id == id).ToList().Count();--%>
<%--var list = db.T_UserInfo.Where(r => r.Id == id).ToList();--%>

<%--var x = new { total = countData, rows = list };--%>

<%--return Content(jsz.Serialize(x));--%>
<%--}--%>
<%--}--%>


<%--}--%>
<%--视图--%>

<%--[csharp] view plain copy--%>
<%--@{--%>
<%--Layout = null;--%>
<%--}--%>

<%--<!DOCTYPE html>--%>

<%--<html>--%>
<%--<head>--%>
<%--<meta name="viewport" content="width=device-width" />--%>
<%--<title>Index9</title>--%>
<%--<script src="~/Scripts/jquery-1.8.2.js"></script>--%>
<%--<script src="~/jquery-easyui/jquery.easyui.min.js"></script>--%>
<%--<script src="~/jquery-easyui/jquery.easyui.min.js"></script>--%>
<%--<script src="~/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>--%>
<%--<link href="~/jquery-easyui/themes/default/easyui.css" rel="stylesheet" />--%>
<%--<link href="~/jquery-easyui/themes/icon.css" rel="stylesheet" />--%>
<%--</head>--%>
<%--<body>--%>


<%--<table id="Cse_Bespeak_Log" class="easyui-datagrid" style="width: auto; height: 350px;">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<!--可以详写，也可以简写，详写如：data-options="field:'Id'"，简写如：field="Name"-->--%>
<%--@*<th data-options="field:'Id'" align="center" width="100" sortable="true">--%>
<%--编号--%>
<%--</th>--%>
<%--<th field="UserName" align="center" width="120" sortable="true">--%>
<%--用户名--%>
<%--</th>--%>
<%--<th field="Name" align="center" width="80" sortable="true">--%>
<%--姓名--%>
<%--</th>--%>
<%--<th field="Age" align="center" width="80" sortable="true">--%>
<%--年龄--%>
<%--</th>--%>
<%--<th field="Email" align="center" width="80" sortable="true">--%>
<%--邮箱--%>
<%--</th>*@--%>
<%--@*<th align="center" width="120px">操作</th>*@--%>
<%--</tr>--%>
<%--</thead>--%>
<%--</table>--%>

<%--<!--这是一个弹出窗口easyui-dialog，我在它里面放了一个datagrid-->--%>
<%--<div id="dlg" class="easyui-dialog" style="width: 600px; height: auto; padding: 10px 20px"--%>
<%--data-options="closed:true,buttons:'#dlg-buttons'">--%>
<%--<table id="datagrid" class="easyui-datagrid" style="width:600px;height:150px">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th data-options="field:'Id'" align="center" width="100" sortable="true">--%>
<%--编号--%>
<%--</th>--%>
<%--<th field="UserName" align="center" width="120" sortable="true">--%>
<%--用户名--%>
<%--</th>--%>
<%--<th field="Name" align="center" width="80" sortable="true">--%>
<%--姓名--%>
<%--</th>--%>
<%--<th field="Age" align="center" width="80" sortable="true">--%>
<%--年龄--%>
<%--</th>--%>
<%--<th field="Email" align="center" width="80" sortable="true">--%>
<%--邮箱--%>
<%--</th>--%>

<%--</tr>--%>
<%--</thead>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div id="dlg-buttons">--%>
<%--<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveUser()">保存</a>--%>
<%--<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')">关闭</a>--%>
<%--</div>--%>
<%--<input type="button" value="查询详情" onclick="" />--%>
<%--</body>--%>
<%--</html>--%>
<%--<script type="text/javascript">--%>
<%--$(function () {--%>


<%--$("#Cse_Bespeak_Log").datagrid({--%>
<%--url: "/Home/getData",--%>
<%--iconCls: "icon-add",--%>
<%--fitColumns: false,--%>
<%--loadMsg: "数据加载中......",--%>
<%--pagination: true,--%>
<%--rownumbers: true,--%>
<%--nowrap: false,--%>
<%--showFooter: true,--%>
<%--singleSelect: true,--%>
<%--pageList: [100, 50, 20, 10],--%>

<%--columns: [[--%>
<%--{--%>
<%--field: 'Id', title: '编号', width: 50, align: 'center',--%>
<%--},--%>
<%--{--%>
<%--field: 'UserName', title: '用户名', width: 50, align: 'center',--%>
<%--},--%>
<%--{--%>
<%--field: 'Name', title: '姓名', width: 50, align: 'center',--%>
<%--},--%>
<%--{--%>
<%--field: 'opt', title: '操作', width: 100, align: 'center',--%>
<%--formatter: function (value, row,index) { //参数row表示当前行, 参数index表示当前行的索引值  --%>

<%--//row.Id表示这个button按钮所在的那一行的Id这个字段的值  --%>
<%--var btn = '<input type="button" id='+index+' value="查询详情"  onclick="return LoadUserInfo('+row.Id+')"/>';--%>
<%--return btn;--%>
<%--}--%>
<%--}]]--%>

<%--})--%>

<%--})--%>

<%--function LoadUserInfo(row) {--%>

<%--/*获取选中行*/--%>
<%--//var row = $('#Cse_Bespeak_Log').datagrid('getSelected'); //获取选中行    --%>

<%--$("#datagrid").datagrid({--%>
<%--url: "/Home/getUserInfo?id=" + row,--%>
<%--iconCls: "icon-add",--%>
<%--fitColumns: false,--%>
<%--loadMsg: "数据加载中......",--%>
<%--pagination: true,--%>
<%--rownumbers: true,--%>
<%--nowrap: false,--%>
<%--showFooter: true,--%>
<%--singleSelect: true,--%>
<%--pageList: [100, 50, 20, 10],--%>
<%--})--%>


<%--$('#dlg').window('open');  //弹出这个dialog框  --%>
<%--};--%>

<%--</script>--%>