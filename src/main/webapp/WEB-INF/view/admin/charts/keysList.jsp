<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	response.setHeader("Pragma", "No-Cache");
	response.setHeader("Cache-Control", "No-Cache");
	response.setDateHeader("Expires", 0);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>钥匙开闭锁统计</title>
	<link rel="stylesheet" type="text/css"
		  href="${basePath}/js/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css"
		  href="${basePath}/js/easyui/themes/icon.css" />
	<script type="text/javascript" src="${basePath}/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript"	src="${basePath}/js/easyui/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src="${basePath}/js/easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript"	src="${basePath}/js/easyui/windowControl.js"></script>
	<script type="text/javascript" src="${basePath}/js/calendar/WdatePicker.js"></script>
	<script type="text/javascript">
        var basePath="${basePath}";
        var deptId = "";
        $(function() {
            var infolist = $('#infolist');
            infolist.datagrid( {
                title : '开闭锁报表',
                iconCls : 'icon-users',
                width : '95%',
                height : 560,
                pageSize : 20,
                pageList : [ 20, 30, 50, 100 ],
                nowrap : false,
                striped : true,
                collapsible : false,
                fitColumns : true,
                singleSelect : true,
                url : '${basePath}/chart/keysReport',
                queryParams:{
                    'deptId':deptId
                },
                loadMsg : '数据装载中......',
                remoteSort : false,
                columns : [ [
                    {
                        title : '钥匙编号',
                        field : 'keyName',
                        width : $(this).width() * 0.1,
                        rowspan : 2,
                        align : 'center'
                    },
                    {
                        title : '钥匙地址',
                        field : 'keyCode',
                        width : $(this).width() * 0.1,
                        rowspan : 2,
                        align : 'center'
                    },
                    {
                        title : '次数',
                        field : 'count',
                        width : $(this).width() * 0.2,
                        rowspan : 2,
                        align : 'center'
                    } ] ],
                pagination : true,
                rownumbers : true
            });
            displayMsg();

            function refresh(obj) {
                infolist.datagrid( {
                    url : '${basePath}/chart/keysReport?deptId='+obj,
                    queryParams:{
                        'deptId':deptId
                    },
                    loadMsg : '数据装载中......'
                });
                infolist.datagrid("clearSelections");
                displayMsg();
            }
            
            function displayMsg() {
                infolist.datagrid('getPager').pagination( {
                    beforePageText : '第',
                    afterPageText : '页，共{pages}页',
                    displayMsg : '当前显示从{from}到{to}共{total}记录'
                });
            }
            var addWin;
            function search(){
                addWin = $.createWin( {
                    title : "查询条件",
                    contents:"<table style='font-size:12px;'><tr><td>用户姓名：</td><td><input id=username /></td></tr></table>",
                    width : 300,
                    buttons : [ {
                        text : '查询',
                        iconCls : 'icon-search',
                        handler : query
                    } ]
                });
            }
            function query() {
                infolist.datagrid( {
                    url : basePath+'/user/list',
                    queryParams:{
                        'username':$('#username').val()
                    },
                    loadMsg : '数据装载中......'
                });
                infolist.datagrid("clearSelections");
                displayMsg();
                $.closeWin(addWin);
            }

            $('#tree').tree({
                checkbox: false,
                url: basePath+'/dept/getChildren',
                onBeforeExpand:function(node,param){
                    $('#tree').tree('options').url = basePath+"/dept/getChildren?parentId=" + node.id;
                },
                onClick:function(node){
                    deptId = node.id;
                    refresh(node.id);
                    //getDiss(node.id);
                }
            });
            //获取站点
            function getDiss(obj) {
                var data={
                    "disaId":obj
                };
                $('#dissName').empty();
                $.post(basePath+"/authorization/distribution",data,function(data){
                    var d=JSON.parse(data);
                    for(var i=0;i<d.length;i++){
                        $('#dissName').append("<option value='" + d[i].id + "'>" + d[i].name + "</option>");
                    }
                });
            }

        });
        function searchRpt() {
            var authStartTime=$('#authStartTime').val();
            var authEndTime=$('#authEndTime').val();
            $('#infolist').datagrid( {
                url : '${basePath}/chart/keysReport',
                queryParams:{
                    'deptId':deptId,
					'authEndTime':authEndTime,
					'authStartTime':authStartTime
                },
                loadMsg : '数据装载中......'
            });
            $('#infolist').datagrid("clearSelections");
            displayMsg();
        }
        function displayMsg() {
            $('#infolist').datagrid('getPager').pagination( {
                beforePageText : '第',
                afterPageText : '页，共{pages}页',
                displayMsg : '当前显示从{from}到{to}共{total}记录'
            });
        }
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" height="530">
	<tr>
		<td width="12%" valign="top"
			style="border: 1px solid #99bbe8; border-right: 0;">
			<div class="panel-header" style="border-left: 0; border-right: 0;">区域</div>
			<ul id="tree" style="margin-top: 10px;"></ul>
		</td>
		<td valign="top" style="border: 1px solid #99bbe8;">
			<table style='font-size:12px;'>
				<tr>
					<td>开始日期：</td>
					<td>
						<input id="authStartTime" name="authStartTime" class="easyui-validatebox"  value=""/>
						<img onclick="WdatePicker({el:'authStartTime',dateFmt:'yyyy-MM-dd'})" src="${basePath}/js/calendar/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						-<input id="authEndTime" name="authEndTime" class="easyui-validatebox"    value=""/>
						<img onclick="WdatePicker({el:'authEndTime',dateFmt:'yyyy-MM-dd'})" src="${basePath}/js/calendar/skin/datePicker.gif" width="16" height="22" align="absmiddle">
					</td>
					<td><button onclick="searchRpt()">查询</button></td>
				</tr>
			</table>
			<table id="infolist"></table>
		</td>
	</tr>
</table>
</body>
</html>
