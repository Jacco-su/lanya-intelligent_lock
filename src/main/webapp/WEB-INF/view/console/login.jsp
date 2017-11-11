<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setAttribute("basePath",path);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>智能门禁综合信息管理平台</title>
	<script type="text/javascript">
		window.onload=function(){
			initUser();
			var errormsg = "${errormsg}";
			if(errormsg!=""){
				alert(errormsg);
			}
		};
		//创建Cookie；
		//name：cookie名字；
		//value：cookie值；
		//days：设置的cookie存活时间；超过此时间会自动消失。
		function createCookie(name,value,days)
		{
			if (days)
			{
				var date = new Date();
				date.setTime(date.getTime()+(days*24*60*60*1000));
				var expires = "; expires="+date.toGMTString();
			}
			else var expires = "";
			document.cookie = name+"="+value+expires+"; path=/";
		}

		//读Cookie值
		function readCookie(name)
		{
			var nameEQ = name + "=";
			var ca = document.cookie.split(';');
			for(var i=0;i < ca.length;i++)
			{
				var c = ca[i];
				while (c.charAt(0)==' ') c = c.substring(1,c.length);
				if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
			}
			return null;
		}

		//清除Cookie值
		function eraseCookie(name)
		{
			createCookie(name,"",-1);
		}

		function createCookieObj(){

			//用户名
			var cookie_username_flag = document.all("cookie_username_flag");
			var days = 365*1; //存放一年
			if(cookie_username_flag.checked){
				var cc_logname  = document.getElementById("txtUserName").value;
				createCookie("oa_xlmy_logname",cc_logname,days);
				createCookie("cookie_username_flag","1",days);
			}else{
				eraseCookie("oa_xlmy_logname");
				eraseCookie("cookie_username_flag");
			}

			//密码
			var cookie_password_flag = document.all("cookie_password_flag");
			var days = 365*1; //存放一年
			if(cookie_password_flag.checked){
				var cc_password = document.getElementById("txtPassword").value;
				createCookie("oa_xlmy_password",cc_password,days);
				createCookie("cookie_password_flag","1",days);
			}else{
				eraseCookie("oa_xlmy_password");
				eraseCookie("cookie_password_flag");
			}
		}
		function initUser(){
			//初始化
			var readLogname = readCookie("oa_xlmy_logname");
			var readPW  	= readCookie("oa_xlmy_password");
			var name_flag  	= readCookie("cookie_username_flag");
			var pwd_flag  	= readCookie("cookie_password_flag");
			if(name_flag!=null&&name_flag=="1"){
				document.all("cookie_username_flag").checked="checked";
			}
			if(pwd_flag!=null&&pwd_flag=="1"){
				document.all("cookie_password_flag").checked="checked";
			}
			if(readLogname!=null&&readLogname!=""){
				document.getElementById("txtUserName").value=readLogname;
			}
			if(readPW!=null&&readPW!=""){
				document.getElementById("txtPassword").value=readPW;
			}
		}
		function checkLogin(){
			var txtUserName=document.getElementById("txtUserName").value;
			if(txtUserName==""){
				alert("用户名不能为空");
				document.getElementById("txtUserName").focus();
				return false;
			}
			var txtPassword=document.getElementById("txtPassword").value;
			if(txtPassword==""){
				alert("密码不能为空");
				document.getElementById("txtPassword").focus();
				return false;
			}
			return true;
		}
	</script>
	<style type="text/css">
		<!--
		body,td,th {
			font-family: 微软雅黑;
			font-size: 12px;
			color: #333333;
		}
		body {
			margin:0 auto;
			background-image: url(<%=path %>/images/xlogin/loginbg.jpg);
			background-repeat: no-repeat;
			background-position: center top;
			background-color:#F2F2F2;
			width:1000px;
		}
		a:link {
			color: #333333;
			text-decoration: none;
		}
		a:visited {
			text-decoration: none;
			color: #333333;
		}
		a:hover {
			text-decoration: none;
			color: #01A2AA;
		}
		a:active {
			text-decoration: none;
			color: #01A2AA;
		}
		.login2016{ width:auto; height:455px; padding-top:305px; padding-left:678px; padding-right:48px;}
		#logtab{}
		#logtab td{height:55px;}
		.reglink{ color:#FFFFFF; font-weight:bold; font-size:14px;}
		.reglink a{ color:#FFFFFF!important;}
		#logtab .input1{
			background-image: url(<%=path %>/images/xlogin/login_3.jpg);
			background-repeat: no-repeat;
			background-position: left center;
			width:224px;
			height:38px;
			line-height:38px;
			background-color:#FFFFFF;
			padding-left:50px;
			border:0px #fff solid;
			color:#666666;
			font-size:14px;
			border-radius:3px;
		}

		#logtab .input2{
			background-image: url(<%=path %>/images/xlogin/login_6.jpg);
			background-repeat: no-repeat;
			background-position: left center;
			width:224px;
			height:38px;
			line-height:38px;
			background-color:#FFFFFF;
			padding-left:50px;
			border:0px #fff solid;
			color:#666666;
			font-size:14px;
			border-radius:3px;
		}
		.bottom2016{ width:1000px; height:105px; padding-top:35px; line-height:35px; text-align:center; color:#878787;}
		.bottom2016 a{ color:#878787!important;}

		-->
	</style>
</head>

<body>
<form id="loginForm" name="loginForm" action="${basePath}/log/userlogin" method="post" onsubmit="return checkLogin()">
<div class="login2016">

	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="logtab">
		<tr>
			<td><input name="loginName" id="txtUserName"  type="text" class="input1" /></td>
		</tr>
		<tr>
			<td><input name="loginPwd" type="password" id="txtPassword"  class="input2" /></td>
		</tr>
		<tr>
			<td>
				<input type="checkbox" name="cookie_username_flag" id="cookie_username_flag" value="1"><span class="white">记住用户名</span>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="cookie_password_flag" type="checkbox"	name="cookie_password_flag" value="1" /><span class="white">记住密码</span>
			</td>
		</tr>
		<tr>
			<td valign="middle">
				<a href="javascript:void(0)" onclick="createCookieObj()">
					<input type="image" src="<%=path %>/images/xlogin/login_14.jpg" width="274" height="40" border="0" /></a></td>
		</tr>
	</table>
</div>
</form>

<div class="bottom2016">
	Copyrigth@郑州市海威光电&nbsp;&nbsp;&nbsp;&nbsp;技术支持： <a href="http://www.flysent.cn" class="reglink" target="_blank">海威光电</a> 服务电话:0371-</div>

</body>
</html>
