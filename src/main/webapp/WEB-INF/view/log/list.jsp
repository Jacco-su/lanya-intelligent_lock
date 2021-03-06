<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setContentType("text/html;charset=UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>日志管理</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/js/easyui/themes/icon.css"/>
	<script type="text/javascript" src="${basePath}/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/easyui/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src="${basePath}/js/easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${basePath}/js/easyui/windowControl.js"></script>
	<script type="text/javascript" src="${basePath}/js/easyui/toolbar.js"></script>
	<script type="text/javascript" src="${basePath}/js/calendar/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		var infolist = $('#infolist');
		infolist.datagrid( {
			title : '日志管理',
			iconCls : 'icon-users',
			width : '95%',
			height : 560,
			pageSize : 30,
			pageList : [30],
			nowrap : false,
			striped : true,
			collapsible : true,
			fitColumns : true,
			method:'GET',
			url : '${basePath}/log/list',
			loadMsg : '数据装载中......',
			sortName : 'id',
			sortOrder : 'desc',
			remoteSort : false,
			singleSelect:true,
			onDblClickRow:function(rowIndex, field, value){
				var rows=infolist.datagrid("getRows");
				var id=rows[rowIndex].id;
				detail(id);
			},
			columns : [ [
			{
				title : '序号',
				field : 'id',
				width : $(this).width() * 0.2,
				align : 'left'
			},
				{
					title : '类型',
					field : 'type',
                    width: $(this).width() * 0.1,
					align : 'left',
					formatter : function(value,rowData,rowIndx) {
						if(value==0)value="登录";
						if(value==1)value="新增";
						if(value==2)value="更新";
						if(value==3)value="删除";
                        if(value==4)value="设备";
                        if(value==5)value=="授权申请";
                        if(value==9)value="其它";
						return value;
					}
				},
                {
                    title : '用户',
                    field : 'username',
                    width: $(this).width() * 0.1,
                    align : 'left'
                },
				{
					title : '描述',
					field : 'content',
					width : $(this).width() * 0.2,
					align : 'left'
				},
				{
					title : 'ip',
					field : 'ip',
                    width: $(this).width() * 0.1,
					align : 'left'
				},
				{
					title : '创建时间',
					field : 'createTime',
					width : $(this).width() * 0.2,
					align : 'left'
				}
			] ],
			pagination : true,
			rownumbers : true
//			toolbar : [{
//				text : '删除',
//				iconCls : 'icon-remove',
//				handler : del
//			}]
		});
		displayMsg();

		function refresh() {
			infolist.datagrid("clearSelections"); 
			infolist.datagrid("reload");
			displayMsg();
		}
		function displayMsg() {
			infolist.datagrid('getPager').pagination( {
				beforePageText : '第',
				afterPageText : '页，共{pages}页',
				displayMsg : '当前显示从{from}到{to}共{total}记录'
			});
		}
		var id;
		var addWin;
		var updateWin;
		var seeWin;
		
		function getSelect() {
			var select = infolist.datagrid('getSelected');
			if (select) {
				$('#addFrom').window('open');
			} else {
				$.messager.alert('警告', '请选择一行数据', 'warning');
			}
		}

		function seedetail() {
			var select = infolist.datagrid('getSelected');
			if (select) {
				detail(select.id);
			} else {
				$.messager.alert('警告', '请选择一行数据', 'warning');
			}
		}

		function del() {
			var id = "";
			var selections = infolist.datagrid('getSelections');
			if (selections.length > 0) {
				for (i = 0; i < selections.length; i++) {
					//id = id + selections[0].id + ',';
				}
				id=selections[0].id;
				$.messager.confirm('警告', '确认删除么?', function(f) {
					if (f) {
						$.ajax( {
							type : "POST",
							url : "${basePath}/log/delete",
							data : "id=" + id,
							dataType : "json",
							cache : false,
							success : function(json) {
								$.messager.alert('提示',json.message, 'warning');
								refresh();
							}
						});
					}
				});
			} else {
				$.messager.alert('警告', '请选择一行数据', 'warning');
			}
		}
		function query() {
			var queryParams = infolist.datagrid('options').queryParams;
			queryParams.queryWord = $('#qq').val();
			infolist.datagrid( {
				url : 'easyQuery.action'
			});
			displayMsg();
			$('#query').window('close');
		}
        $('#tree').tree({
            checkbox: false,
            url: '${basePath}/dept/getChildren',
            onBeforeExpand:function(node,param){
                $('#tree').tree('options').url = "${basePath}/dept/getChildren?parentId=" + node.id;
            },
            onClick:function(node){
                deptId = node.id;
                getUsers(deptId);
            }
        });
        function getUsers(obj) {
            var data={
                "disaId":obj
            };
            //获取使用人
            $.post("${basePath}/authorization/user",data,function(data){
                var d=JSON.parse(data);
                for(var i=0;i<d.length;i++){
                    $('#userId').append("<option value='" + d[i].username + "'>" + d[i].username + "</option>");
                }
            });
        }
	});
    function searchUser() {
        $('#infolist').datagrid( {
            url : '${basePath}/log/list',
            queryParams:{
                'userId':$('#userId').val()
            },
            loadMsg : '数据装载中......'
        });
        $('#infolist').datagrid("clearSelections");
        //displayMsg();
    }
    function displayMsg() {
        $('#infolist').datagrid('getPager').pagination({
            beforePageText: '第',
            afterPageText: '页，共{pages}页',
            displayMsg: '当前显示从{from}到{to}共{total}记录'
        });
    }
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" height="630">
	<tr>
		<td width="12%" valign="top"
			style="border: 1px solid #99bbe8; border-right: 0;">
			<div class="panel-header" style="border-left: 0; border-right: 0;">区域</div>
			<ul id="tree" style="margin-top: 10px;"></ul>
		</td>
		<td valign="top" style="border: 1px solid #99bbe8;">
			<table style='font-size:12px;'>
				<tr>
					<td>用户：</td>
					<td><select id='userId' style='width: 150px;'></select>
					</td>
					<td><button onclick="searchUser()">查询</button></td>
				</tr>
			</table>
			<table id="infolist"></table>
		</td>
	</tr>
</table>
</body>
</html>
