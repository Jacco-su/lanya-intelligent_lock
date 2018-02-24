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
	<title>开闭锁统计</title>
	<link rel="stylesheet" type="text/css"
		  href="${basePath}/js/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css"
		  href="${basePath}/js/easyui/themes/icon.css" />
	<script type="text/javascript" src="${basePath}/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript"	src="${basePath}/js/easyui/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src="${basePath}/js/easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
	<script type="text/javascript"	src="${basePath}/js/easyui/windowControl.js"></script>
	<script type="text/javascript"	src="${basePath}/js/easyui/validate.js"></script>
	<script type="text/javascript">
        var basePath="${basePath}";
        $(function() {
            var deptId = "";
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
                url : '${basePath}/user/list',
                queryParams:{
                    'deptId':deptId
                },
                loadMsg : '数据装载中......',
                remoteSort : false,
                onDblClickRow:function(rowIndex, field, value){
                    var rows=infolist.datagrid("getRows");
                    var id=rows[rowIndex].id;
                    showEdit(id);
                },
                columns : [ [
                    {
                        title : '站点',
                        field : 'rdate',
                        width : $(this).width() * 0.1,
                        rowspan : 2,
                        align : 'center'
                    }, {
                        title : '次数',
                        field : 'roles',
                        formatter : function(value, rec) {
                            var t = "";
                            $.each(value, function(i, v) {
                                t += v.name + " ";
                            });
                            return t;
                        },
                        width : $(this).width() * 0.2,
                        rowspan : 2,
                        align : 'center'
                    } ] ],
                pagination : true,
                rownumbers : true
            });
            displayMsg();

            function refresh() {
                infolist.datagrid( {
                    url : '${basePath}/user/list',
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
                    refresh();
                }
            });

        });
	</script>
</head>
<body>
<table style='font-size:12px;'>
	<tr>
		<td>区域：</td>
		<td><input id=areaCode" />
		</td>
	</tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td width="12%" valign="top" style="border: 1px solid #99bbe8; border-right: 0;">
			<div style="width: 100%;">
				<div class="panel-header" style="border-left: 0; border-right: 0;">区域</div>
				<ul id="tree" style="margin-top: 10px;  height: 500px; overflow: scroll;">
				</ul>
			</div>
		</td>
		<td valign="top">
			<table id="infolist"></table>
		</td>
	</tr>
</table>
</body>
</html>
