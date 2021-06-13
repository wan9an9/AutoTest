<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ page import="test.dao.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>教师端</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>

  </head>
  
  <body>
    <div id="headnav" style="
			position:absolute; 
		  	left:0; top:0; 
		  	width:100%;
		  	height:60px;
		  	background-color: red;">
		  	<div id="sysname" style="
		  		position:absolute; 
		  	left:0; top:0; 
		  	width:200px;
		  	height:60px;
		  	z-index: 999;
		  	text-align: center;">
		  	<h1 style="color: white; font-style:italic;">主观题评分系统</h1>
			<h3 style="color: yellow;">教师端</h3>
		</div>
		
		<ul class="layui-nav  layui-bg-black"  lay-filter="headnav" style="text-align: right;border-radius: 0;">
  <li class="layui-nav-item">
    <a><img src="image/touxiang.jpg" class="layui-nav-img" style="height:60px;width: 60px;"></a>
    <dl class="layui-nav-child" style="text-align: center;">
      <dd><a onclick="exit()">退出</a></dd>
    </dl>
  </li>
</ul>
</div>

<div id="myinfo" style="
	position:absolute; 
		  	left:0; top:60px; 
		  	width:200px;
		  	height:70px;
		  	background-color: #2F4056;">
		  	
		  	<div id="touxiang" style="
		  		position:absolute; 
		  	left:10px; top:10px; 
		  	width:50px;
		  	height:50px;
		  	border-radius: 50%;
		  	background-image:url('image/touxiang.jpg');
              	background-size: cover;
		  	background-position:center;"></div>
		  	<div id="info" style="
		  		position: absolute;
		  		left:70px; top:13px; 
		  	width:120px;
		  	height:50px;
		  	text-align: left;">
		  	<%
	        List<TeacherBean> teacherinfo = (List<TeacherBean>)request.getSession().getAttribute("session_teacher");
	        %>
		  	<h3 style="color: white"><%=teacherinfo.get(0).getTid()%></h3>
		  	<h3 style="color: white"><%=teacherinfo.get(0).getTname()%></h3>
</div>
</div>
		  
<div id="menu" style="
	position:absolute; 
		  	left:0; top:130px; 
		  	width:200px;
		  	height:20px;
		  	text-align: center;
		  	background-color: #1A2226"><h5 style="color: #555555;">主菜单</h5></div>

<div id="sidenav" style="
	position:absolute; 
		  	left:0; top:150px; 
		  	width:200px;">
<ul class="layui-nav layui-nav-tree layui-nav-side" lay-shrink="all" style="top:150px;border-radius: 0;">
	
	<li class="layui-nav-item layui-nav-itemed">
    <a href="javascript:;" id="sidenavli1">首页</a>
        <dl class="layui-nav-child">
      <dd><a onclick="teacherlist()" id="teacherlistonclick" class="layui-this">我的授课</a></dd>
      <dd><a id="testonclick">我的考试</a></dd>
    </dl>
  </li>
	
  <li class="layui-nav-item">
    <a href="javascript:;" id="sidenavli2">教师信息</a>
    <dl class="layui-nav-child">
      <dd><a onclick="tudtpwd()" id="tudtpwdonclick">修改密码</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item">
    <a href="javascript:;" id="sidenavli3">学生信息</a>
    <dl class="layui-nav-child">
    	<dd><a onclick="tudspwd()" id="tudspwdonclick">修改密码</a></dd>
    	<dd><a onclick="treg()" id="tregclick">学生注册</a></dd>
    	<dd><a onclick="sviewtb()" id="sviewtbclick">学生列表</a></dd>
    </dl>
  </li>
<li class="layui-nav-item">
    <a href="javascript:;" id="sidenavli4">试题管理</a>
    <dl class="layui-nav-child">
    	<dd><a onclick="editquestion()" id="editquestiononclick">编辑试题</a></dd>
    	<dd><a onclick="publishquestion()" id="publishquestiononclick">发布试题</a></dd>
    </dl>
</li>
  <li class="layui-nav-item">
    <a href="javascript:;" id="sidenavli5">试题评阅</a>
    <dl class="layui-nav-child">
    	<dd><a onclick="markbycomputer()" id="markbycomputeronclick">自动评阅</a></dd>
    	<dd><a onclick="markbyteacher()" id="markbyteacheronclick">人工评阅</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item">
    <a href="javascript:;" id="sidenavli6">成绩分析</a>
    <dl class="layui-nav-child">
    	<dd><a id="markcompare">评分对比</a></dd>
    </dl>
  </li>
  
</ul>
	</div>
	
<div id="teacherlistarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;">
		  	<div id="homediv" style="
		  		position:absolute; 
		  	left: 100px; top:0;">
<table class="layui-hide" id="tb_teacherinfo"></table></div></div>

<div id="testlistarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<div style="position:absolute;left: 10px; height: 30px;text-align: right;">
<h2 style="color: red;">*请提前12个小时以上修改考试时间！</h2>
</div>
<div style="position:absolute; left: 10px; top:30px;">
<table class="layui-hide" id="tb_testinfo" lay-filter="tb_testinfo"></table>
</div>
</div>
	
<div id="tudtpwdarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
		  	<div id="pwdform1" style="
		  		position:absolute; 
		  	left: 0; top:0;">
<form class="layui-form" action="UpdatePwdServlet" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">
    	<i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #009688;" onmousedown="pwdshow()" onmouseup="pwdhidden()"></i>
    </label>
    <div class="layui-input-block">
      <input type="password" id="inputnewpwd1" name="tnewpassword" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
      <button type="submit" class="layui-btn" lay-submit style="position:absolute; top: 0; margin-left: 220px ;" onclick="uptpwd">修改</button>
    </div>
  </div>
  </form>
  </div>
	</div>
	
<div id="tudspwdarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
		  	<div id="pwdform2" style="
		  		position:absolute; 
		  	left: 0; top:0;">
<form class="layui-form" action="UpdatePwdServlet" method="post">
  <div class="layui-form-item">
  	<label class="layui-form-label">
    	<i class="layui-icon layui-icon-diamond" style="font-size: 30px; color: #009688;"></i>
    </label>
  	<div class="layui-input-block">
      <input type="text" name="sidudpwd" required lay-verify="required" placeholder="请输入学生学号" autocomplete="off" class="layui-input">
  	</div></div>
  	<div class="layui-form-item">
    <label class="layui-form-label">
    	<i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #009688;" onmousedown="pwdshow()" onmouseup="pwdhidden()"></i>
    </label>
    <div class="layui-input-block">
      <input type="password" id="inputnewpwd2" required lay-verify="required" name="tsnewpassword" placeholder="请输入新密码" autocomplete="off" class="layui-input">
      <button type="submit" class="layui-btn" lay-submit lay-filter="updatepwd" style="margin-top: 10px; margin-left: 40px ;">修改</button>
    </div>
  </div>
  </form>
</div>
	</div>
	
<div id="tregarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
		  	<div id="regform" style="
		  		position:absolute; 
		  	left: 0; top:0;">
<form class="layui-form" action="SRegisterServlet" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">学号</label>
    <div class="layui-input-block">
      <input type="text" name="regid" required  lay-verify="required" placeholder="请输入学号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-form-mid layui-word-aux" style="left: 120px;"><h4 style="color: red;">* 初始密码和学号相同。</h4></div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block">
      <input type="text" name="regname" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="regsex" value="男" title="男" checked>
      <input type="radio" name="regsex" value="女" title="女" >
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">班级</label>
    <div class="layui-input-block">
      <select name="regclass" lay-verify="required">
      <%
      	String classstr = "";
      	for(int i=0; i<teacherinfo.size(); i++){
      		classstr = classstr + teacherinfo.get(i).getTclasses() + ",";
      	}
      	String [] classes = classstr.split(",|，| ");
      	//去重
      	List classlist = new ArrayList();
      	for(int j=0; j<classes.length; j++){
      		if(!classlist.contains(classes[j])){
      			classlist.add(classes[j]);
      		}
      	}
      	Object[] unclasses = classlist.toArray();
      	for(int k=0; k<unclasses.length; k++)
      	{
       %>
        <option value="<%=unclasses[k]%>"><%=unclasses[k]%></option>
        <%} %>
      </select>
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">待考科目</label>
    <div class="layui-input-block">
    <%
    	String subjectstr = "";
      	for(int i=0; i<teacherinfo.size(); i++){
      		subjectstr = subjectstr + teacherinfo.get(i).getTsubjects() + ",";
      	}
      	String [] subjects = subjectstr.split(",|，| ");
      	for(int i=0; i<subjects.length; i++)
      	{
     %>
      <input type="checkbox" name="checksubjects" value="<%=subjects[i]%>" title="<%=subjects[i]%>">
      <%} %>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button type="submit"class="layui-btn" lay-submit lay-filter="formDemo">注册</button>
      <button type="reset" class="layui-btn layui-btn-pridimary">重置</button>
    </div>
  </div>
  </form>
</div>
	</div>
	
<div id="sviewtbarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<div id="tbsviewclasschoose" style="
		  		position:absolute; 
		  	left: 0; top:0;">
<form class="layui-form" action="SShowInfoServlet" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">选择班级</label>
    <div class="layui-input-block">
      <select name="tbsviewclasschoose" lay-verify="required" id="tbsviewclasschoose">
      <%
      	for(int i=0; i<unclasses.length; i++)
      	{
       %>
        <option value="<%=unclasses[i]%>"><%=unclasses[i]%></option>
        <%} %>
      </select>
      <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo" style="position:absolute;left: 250px;top:0" onclick="showclassinfotb()">查看</button>
    </div>
  </div>
  </form>
 </div>
 <div id="classtbtitle" class="layui-form-item" style="position:absolute; left: 20px;top: 60px;display:none;">
    <div class="layui-form-mid layui-word-aux"><h2 id="sbh2" style="color: black;"></h2></div>
  </div>
		  	<div id="tbsview" style="
		  		position:absolute; 
		  	left: 0; top:100px;
		  	height: 40px;
		  	height: 80%;
		  	display:none;">
		  	<div class="layui-form-item">
		  		<table class="layui-hide" id="tb_sview"></table>
		  	</div>
	</div>
	</div>
	
<div id="editquestionarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display:none;">
<form class="layui-form">
<div class="layui-form-item">
    <label class="layui-form-label">选择科目</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="editquestionsubject" lay-verify="required" id="editquestionsubject">
      <option value="zero">请选择科目</option>
      <%
      	for(int i=0; i<subjects.length; i++)
      	{%>
        <option value="<%=subjects[i]%>"><%=subjects[i]%></option>
        <%} %>
      </select>
    </div>
 </div>
<div class="layui-form-item" style="position:absolute;top: 0;left:300px;">
    <label class="layui-form-label">题目数量</label>
    <div class="layui-input-block">
      <input type="text" name="qcount" id="qcount" lay-verify="required|number" placeholder="多于实际数量会附加" autocomplete="off" class="layui-input">
    </div>
  </div>
<div class="layui-form-item" id="div_questioncount" style="position:absolute;top: 0;left:620px;">
      <button type="button"class="layui-btn" id="bt_questioncount">实际数量</button>
 </div>
 <div class="layui-form-item" id="div_editquestion" style="position:absolute;top: 0;left:750px;">
      <button type="button"class="layui-btn" id="bt_editquestion">开始编辑</button>
 </div>
 <div class="layui-form-item" id="div_exitquestion" style="position:absolute;top: 0;left:750px;display: none;">
      <button type="button"class="layui-btn" id="bt_exitquestion">退出编辑</button>
 </div>
</form>
<div id=div_editquestion style="
		  		position:absolute; 
		  	left: 10px; top:40px;">
		  	<table class="layui-hide" id="tb_editquestion" lay-filter="tb_editquestion"></table>
	</div>
</div>

<div id="publishquestionarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display:none;">
	<form class="layui-form">
		<div class="layui-form-item">
    <label class="layui-form-label">选择科目</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="publishquestionsubject" id="publishquestionsubject" lay-filter="publishquestionsubject">
      	<option value="zero">请选择科目</option>
      <%
      	for(int i=0; i<subjects.length; i++)
      	{%>
        <option value="<%=subjects[i]%>"><%=subjects[i]%></option>
        <%} %>
      </select>
    </div>
 	</div>
 	<div class="layui-form-item" style="position:absolute;top: 0;left:300px;">
    <label class="layui-form-label">选择班级</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="publishquestionclasses" id="publishquestionclasses">
      </select>
    </div>
 	</div>
 <div class="layui-form-item" style="position:absolute;top: 0;left:640px;">
      <button type="button"class="layui-btn" id="bt_lookquestion">查看试题</button>
 </div>
    <div class="layui-inline" style="position:absolute;top: 0;left:770px;">
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="begintime" placeholder="开考时间【年月日时分秒】">
      </div>
    </div>
        <div class="layui-inline" style="position:absolute;top: 0;left:970px;">
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="endtime" placeholder="结束时间【年月日时分秒】">
      </div>
    </div>
 <div class="layui-form-item" id="div_publishquestion" style="position:absolute;top: 0;left:1180px;">
      <button type="button"class="layui-btn" id="bt_publishquestion">发布考试</button>
 </div>
	</form>
	<div id=div_lookquestion style="
		  		position:absolute; 
		  	left: 10px; top:40px;">
		  	<table class="layui-hide" id="tb_lookquestion" lay-filter="tb_lookquestion"></table>
	</div>
</div>

<div id="markbyteacherarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<form class="layui-form">
<div class="layui-form-item" id="div_markbyteachersubject">
    <label class="layui-form-label">选择科目</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="markbyteachersubject" id="markbyteachersubject" lay-filter="markbyteachersubject">
      	<option value="zero">请选择科目</option>
      <%
      	for(int i=0; i<subjects.length; i++)
      	{%>
        <option value="<%=subjects[i]%>"><%=subjects[i]%></option>
        <%} %>
      </select>
    </div>
 	</div>
 	<div class="layui-form-item" style="position:absolute;top: 0;left:300px;" id="div_markbyteacherclasses">
    <label class="layui-form-label">选择班级</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="markbyteacherclasses" id="markbyteacherclasses">
      </select>
    </div>
 	</div>
 <div class="layui-form-item" style="position:absolute;top: 0;left:640px;" id="div_beginmark">
      <button type="button"class="layui-btn" id="bt_beginmark">开始评阅</button>
 </div>
 <div id="div_markbyteacherhead" style="position:absolute; height: 40px; left:0;top: 0; width: 1320px;display: none;">
<div class="layui-form-item" style="position:absolute;left:25px;width: 500px;height: 40px;">
    <h1 id="h1_onesubject">科目</h1>
</div>
<div class="layui-form-item" style="position:absolute;left:525px;width: 350px;height: 40px;">
    <h1 id="h1_oneclass">xx班</h1>
</div>
<div class="layui-form-item" style="position:absolute;left:875px;width: 100px;height: 40px;display: none;">
    <h1 id="h1_pagetype">答题卡</h1>
</div>
 <div class="layui-form-item" style="position:absolute;left:1020px;" id="div_markover">
      <button type="button"class="layui-btn" id="bt_markover">提交评分</button>
 </div>
  <div class="layui-form-item" style="position:absolute;left:1020px;display: none;" id="div_looktpestcard">
      <button type="button"class="layui-btn" id="bt_lookptestcard">查看成绩单</button>
 </div>
 <div class="layui-form-item" style="position:absolute;left:1200px;">
      <button type="button"class="layui-btn" id="bt_exitmark">退出评阅</button>
 </div>
</div>
</form>
<div id="div_markbyteacher" style="
		  		position:absolute; 
		  	left: 10px; top:50px;">
		  	<table class="layui-hide" id="tb_markbyteacher" lay-filter="tb_markbyteacher"></table>
</div>
<div id="div_ptestcard" style="
		  		position:absolute; 
		  	left: 300px; top:50px;display: none;">
		  	<table class="layui-hide" id="tb_ptestcard" lay-filter="tb_ptestcard"></table>
</div>
</div>

<div id="markbycomputerarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<form class="layui-form">
<div class="layui-form-item" id="div_markbycomputersubject">
    <label class="layui-form-label">选择科目</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="markbycomputersubject" id="markbycomputersubject" lay-filter="markbycomputersubject">
      	<option value="zero">请选择科目</option>
      <%
      	for(int i=0; i<subjects.length; i++)
      	{%>
        <option value="<%=subjects[i]%>"><%=subjects[i]%></option>
        <%} %>
      </select>
    </div>
 	</div>
 <div class="layui-form-item" style="position:absolute;top: 0;left:350px;" id="div_automark">
      <button type="button"class="layui-btn" id="bt_automark">一键评阅</button>
 </div>
 <div id="div_markbycomputerhead" style="position:absolute; height: 40px; left:0;top: 0; width: 1320px;display: none;">
 <div class="layui-form-item" id="div_twosubject" style="position:absolute;left:25px;width: 500px;height: 40px;">
    <h1 id="h1_twosubject">科目：</h1>
</div>
<div class="layui-form-item" id="div_pagetype2" style="position:absolute;left:550px;width: 120px;height: 40px;display: none;">
    <h1 id="h1_pagetype2">评阅结果</h1>
</div>
 <div class="layui-form-item" style="position:absolute;top: 0;left:820px;" id="div_looktestcard2">
      <button type="button"class="layui-btn" id="bt_looktestcard2">成绩单</button>
 </div>
  <div class="layui-form-item" style="position:absolute;top: 0;left:1000px;" id="div_automarkres">
      <button type="button"class="layui-btn" id="bt_automarkres">评阅结果</button>
 </div>
  <div class="layui-form-item" style="position:absolute;left:1200px;" id="div_exitmark2">
      <button type="button"class="layui-btn" id="bt_exitmark2">退出评阅</button>
 </div>
 </div>
 <div id="div_cmarkresult" style="
		  		position:absolute; 
		  	left: 0; top:50px;display: none;">
		  	<table class="layui-hide" id="tb_cmarkresult" lay-filter="tb_cmarkresult"></table>
</div>
<div id="div_ctestcard" style="
		  		position:absolute; 
		  	left: 130px; top:50px;display: none;">
		  	<table class="layui-hide" id="tb_ctestcard" lay-filter="tb_ctestcard"></table>
</div>
<div style="position:absolute;left:400px;width: 500px;height: 530px;display: none;" id="div_automarkloading">
  <div style="position:absolute;left:0;top:0;width: 500px;height: 500px;">
       <image src="image/loading_automark.gif"></image>
 </div>
 <div style="position:absolute;left:0;top:500px;width: 500px;height: 30px;text-align: center;">
 		<h2 style="font-size: large;">orz  正在评阅   请稍等...</h2>
 </div>
 </div>
</form>
</div>

<div id="markcomparearea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<div id="markcomparehead" style="position:absolute; height: 40px; left:200px;top: 0; width: 1000px;">
	<h1 style="position:absolute;left:0;width:500px;">网络17k1班  “思修” 评分结果对照表</h1>
	<h2 style="position:absolute;top:10px;left:500px;width: 500px;color: red;">某题分值 = Σ(该题每名学生得分) / 班级学生人数20</h2>
</div>
<div id="ECharts" style="position:absolute; height: 500px; left:100px;top: 100px; width: 1150px;">
	
</div>
</div>

<script type="text/html" id="bar_editquestion">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script type="text/html" id="bar_lookquestion">
  <a class="layui-btn layui-btn-xs" lay-event="look">详情</a>
</script>
<script type="text/html" id="bar_markbyteacher">
  <a class="layui-btn layui-btn-xs" lay-event="mark">评阅</a>
</script>
<script type="text/html" id="bar_altertime">
  <a class="layui-btn layui-btn-xs" lay-event="altertime">修改时间</a>
</script>
<script type="text/html" id="bar_cmarkresult">
  <a class="layui-btn layui-btn-xs" lay-event="look">详情</a>
</script>

<script type="text/javascript" charset="utf-8" src="layui/layui.js" ></script>
<script src="ECharts/echarts.min.js"></script>
<script type="text/javascript">
        layui.use(['form','layer','carousel','table','jquery','laydate'],function(){
  	 		var form = layui.form;
  	 		var layer= layui.layer;
  	 		var carousel = layui.carousel;
  	 		var table = layui.table; 
  	 		var $ = layui.$;
  	 		var laydate = layui.laydate;

  //日期时间选择器
  laydate.render({
    elem: '#begintime'
    ,type: 'datetime'
  });   
  laydate.render({
    elem: '#endtime'
    ,type: 'datetime'
  });
//老师首页表
table.render({
    elem: '#tb_teacherinfo'
    ,url: 'LoginServlet'
    ,even:true
    ,height:605
    ,limits:[5,11,20]
    ,limit:11
    ,page:true
    ,cols: [[ //表头
      {field: 'tid', title: '工号', width:150,unresize:true}
      ,{field: 'tname', title: '姓名', width:100,unresize:true}
      ,{field: 'tsex', title: '性别', width:70,unresize:true}
      ,{field: 'tclasses', title: '教授班级', width:400,sort: true,unresize:true} 
      ,{field: 'tsubjects', title: '教授科目', width: 400,unresize:true}
    ]]
  });
  //某班学生表
   table.render({
    elem: '#tb_sview'
    ,url: 'SShowInfoServlet'
    ,even:true
    ,height:505
    ,limits:[5,11,20]
    ,limit:11
    ,page:true
    ,cols: [[
      {field: 'sid', title: '学号', width:150,sort: true,unresize:true}
      ,{field: 'sname', title: '姓名', width:100,unresize:true}
      ,{field: 'ssex', title: '性别', width:70,unresize:true}
      ,{field: 'sclass', title: '班级', width:150,unresize:true} 
      ,{field: 'ssubjects', title: '考试科目', width: 400,unresize:true}
    ]]
  });
$("#testonclick").click(function(){
	  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "block";
   document.getElementById("tudtpwdarea").style.display = "none";
    document.getElementById("tudspwdarea").style.display = "none";
    document.getElementById("tregarea").style.display = "none";
    document.getElementById("sviewtbarea").style.display = "none";
    document.getElementById("editquestionarea").style.display = "none";
    document.getElementById("publishquestionarea").style.display = "none";
    document.getElementById("markbyteacherarea").style.display = "none";
    document.getElementById("markbycomputerarea").style.display = "none";
    document.getElementById("markcomparearea").style.display = "none";
table.render({
    elem: '#tb_testinfo'
    ,url: 'AlterTestTimeServlet' //数据接口
    ,method: 'post'
    ,where: {tid: <%=teacherinfo.get(0).getTid()%>}
    ,even:true
    ,height:590
    ,limits:[12,20]
    ,limit:12
    ,page:true
    ,cols: [[
      {field: 'classname', title: '班级', width:300,sort: true,unresize:true}
      ,{field: 'subject', title: '科目', width:300,sort: true,unresize:true}
      ,{field: 'begintime', title: '开考时间', width:200,unresize:true}
      ,{field: 'endtime', title: '结束时间', width:200,unresize:true}
      ,{field: 'ccheckover', title: '机器批改', width:100,unresize:true}
      ,{field: 'pcheckover', title: '教师批改', width:100,unresize:true}
     ,{field: 'right', title: '操作', width:100,toolbar:'#bar_altertime'}
    ]]
  });
});
  //监听行工具事件  修改考试时间
  table.on('tool(tb_testinfo)', function(obj){
    var data = obj.data;
    var flag = true;
    			//获取当前时间
				var myDate = new Date();
				var year = myDate.getFullYear();//年  
				var month = myDate.getMonth() + 1;//月 
				var date = myDate.getDate();//日
				var h = myDate.getHours(); //当前小时(0-23)
				var m = myDate.getMinutes(); //当前分钟(0-59)
				var s = myDate.getSeconds(); //当前秒 
				var nowtime = year + '-' + conver(month) + "-" + conver(date) + " " + conver(h) + ':' + conver(m) + ":" + conver(s);
				function conver(s) {
			    	 return s < 10 ? '0' + s : s;
				}
	if(obj.event === 'altertime' && new Date(nowtime).getTime() < new Date(data.begintime).getTime()-43200000){
    	layer.open({
    		type: 2
    		,title: '修改考试时间'
    		,content: 'AlterTestTime.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['确定','取消']
    		,yes:function(index, layero){
    			//回调的数值
    			var classname = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_classname1").innerText;
    			var subject = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_subject1").innerText;
    			var begintime = $(layero).find('iframe')[0].contentWindow.document.getElementById("begintime1").value;
    			var endtime = $(layero).find('iframe')[0].contentWindow.document.getElementById("endtime1").value;
    			var ccheckover = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_ccheckover").innerText;
    			var pcheckover = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_pcheckover").innerText;
    			var begintimevalue = new Date(begintime).getTime();
				var endtimevalue = new Date(endtime).getTime();
					//获取当前时间
				var myDate = new Date();
				var year = myDate.getFullYear();//年  
				var month = myDate.getMonth() + 1;//月 
				var date = myDate.getDate();//日
				var h = myDate.getHours(); //当前小时(0-23)
				var m = myDate.getMinutes(); //当前分钟(0-59)
				var s = myDate.getSeconds(); //当前秒 
				var nowtime = year + '-' + conver(month) + "-" + conver(date) + " " + conver(h) + ':' + conver(m) + ":" + conver(s);
				function conver(s) {
			    	 return s < 10 ? '0' + s : s;
				}
				var time = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			   if(begintime.match(time) == null || endtime.match(time) == null){
			    	layer.msg('日期输入不合法，请重新输入！！');
			    }else if(new Date(nowtime).getTime() >= begintimevalue || new Date(nowtime).getTime() >= endtimevalue){
					layer.msg('输入时间小于等于当前时间！');
				}else if(begintimevalue >= endtimevalue){
					layer.msg('开考时间大于等于结束时间！');
				}else{
				$.ajax({
					url:'AlterTestTimeServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'classname': classname,
    					'subject': subject,
    					'begintime': begintime,
    					'endtime': endtime
    				},
    				success:function(res){
    				if(res == "success"){
    			//同步更新表格数据(非表格重载)
    			obj.update({
    				classname: classname,
    				subject: subject,
    				begintime: begintime,
    				endtime: endtime,
    				ccheckover: ccheckover,
    				pcheckover: pcheckover
    			});
    				layer.msg('修改时间成功！');
    				layer.close(index);
    					}else if(res == "error"){
    						layer.msg('修改时间失败，请重试！');
    					}
    				},
    				error:function(res){
    					layer.msg('修改时间失败，请重试！');
    				}
    			});
				}
    		}
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_altertime');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#h3_classname1').text(data.classname);
    			body.find('#h3_subject1').text(data.subject);
    			body.find('#begintime1').val(data.begintime);
    			body.find('#endtime1').val(data.endtime);
    			body.find('#h3_ccheckover').text(data.ccheckover);
    			body.find('#h3_pcheckover').text(data.pcheckover);
    		}
    	});
    }else{
    	layer.msg('现在已经无法修改考试时间了！');
    }
  });
//编辑题目
  $("#bt_exitquestion").click(function(){
  		window.location.reload(true);
  });
  $("#bt_questioncount").click(function(){
    var opsubject = $("#editquestionsubject option:selected").val();
    if(opsubject== "zero"){
  	layer.msg('未选择科目！');
  	}else{
	    			$.ajax({
					url:'CheckQtbNullServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': opsubject,
    				},
    				success:function(res){
    					$("#qcount").val(res);
    				},
    				error:function(res){
    				}
    			});
    			}
  });
  $("#bt_editquestion").click(function(){
  var opsubject = $("#editquestionsubject option:selected").val();
  var qcount = $("#qcount").val();
  if(opsubject== "zero"){
  	layer.msg('未选择科目！');
  	}
   else if(qcount == ""){
  	layer.msg('请输入题目数量！');
  }else if (!(/(^[1-9]\d*$)/.test(qcount))) { 
  	layer.msg('输入题目数量不合法！');
  }else{
  		  		  $.ajax({
					url:'PublishQServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': opsubject
    				},
    				success:function(res){
    					if(res == "考试已经发布，不可再编辑！"){
    						layer.msg(res);
    					}else if(res == "canedit"){
    						  	$("#div_editquestion").css("display", "none");
 						  		 $("#div_exitquestion").css("display", "block");
 table.render({
    elem: '#tb_editquestion'
    ,url: 'ShowEditQuestionServlet' //数据接口
    ,even:true
    ,method: 'get'
    ,height:590
    ,limits:[6,12,20,50]
    ,limit:12
    ,where: {tid: <%=teacherinfo.get(0).getTid()%>, tsubject: opsubject, qcount: qcount}
    ,page:true
    ,cols: [[
      {field: 'subject', title: '科目', width:150,unresize:true}
      ,{field: 'id', title: '题号', width:80,sort: true,unresize:true}
      ,{field: 'topic', title: '题干', width:350,unresize:true}
      ,{field: 'knowledge', title: '考察点', width:210,unresize:true} 
      ,{field: 'answer', title: '参考答案', width: 350,unresize:true}
      ,{field: 'point', title: '分数', width: 70,unresize:true}
      ,{field: 'right', title: '操作', width: 70,toolbar:'#bar_editquestion'}
    ]]
  });
    					}
    				},
    				error:function(res){
    				}
    			});
  }
  });
  
  //监听行工具事件  编辑题目
  table.on('tool(tb_editquestion)', function(obj){
    var data = obj.data;
	if(obj.event === 'edit'){
    	layer.open({
    		type: 2
    		,title: '编辑题目'
    		,content: 'EditQuestion.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['确定','取消']
    		,yes:function(index, layero){
    			//回调的数值
    			var subject = $(layero).find('iframe')[0].contentWindow.document.getElementById("subject").value;
    			var id = $(layero).find('iframe')[0].contentWindow.document.getElementById("id").value;
    			var topic = $(layero).find('iframe')[0].contentWindow.document.getElementById("topic").value;
    			var knowledge = $(layero).find('iframe')[0].contentWindow.document.getElementById("knowledge").value;
    			var answer = $(layero).find('iframe')[0].contentWindow.document.getElementById("answer").value;
    			var point = $(layero).find('iframe')[0].contentWindow.document.getElementById("point").value;
    			$.ajax({
					url:'EditQuestionServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': subject,
    					'id': id,
    					'topic': topic,
    					'knowledge': knowledge, 
    					'answer': answer,
    					'point': point
    				},
    				success:function(res){
    				if(res == "success"){
    			//同步更新表格数据(非表格重载)
    			obj.update({
    				subject: subject,
    				id: id,
    				topic: topic,
    				knowledge: knowledge,
    				answer: answer,
    				point: point
    			});
    					layer.msg('编辑成功！');
    					}else if(res == "error"){
    						layer.msg('编辑失败，请重试！');
    					}
    				},
    				error:function(res){
    					layer.msg('编辑失败，请重试！');
    				}
    			});
    			layer.close(index);
    		}
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_editq');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#subject').val(data.subject);
    			body.find('#id').val(data.id);
    			body.find('#topic').val(data.topic);
    			body.find('#knowledge').val(data.knowledge);
    			body.find('#answer').val(data.answer);
    			body.find('#point').val(data.point);
    		}
    	});
    }
  });
 //（发布题目）下拉框二级联动
form.on('select(publishquestionsubject)',function(data){
	var opsubject = data.value;
	    			$.ajax({
					url:'TShowInfoServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': opsubject,
    				},
    				success:function(res){
    					var tmp = '<option value="allclasses">全部班级</option>';
    					for(var i in res){
    						tmp += '<option value="' + res[i] + '">' + res[i] +'<option>';
    					}
    					$("#publishquestionclasses").html(tmp);
    					form.render();
    				},
    				error:function(res){
    				}
    			});
});
 //（人工评阅）下拉框二级联动
form.on('select(markbyteachersubject)',function(data){
	var opsubject = data.value;
	    			$.ajax({
					url:'TShowInfoServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': opsubject,
    				},
    				success:function(res){
    					var tmp;
    					for(var i in res){
    						tmp += '<option value="' + res[i] + '">' + res[i] +'<option>';
    					}
    					$("#markbyteacherclasses").html(tmp);
    					form.render();
    				},
    				error:function(res){
    				}
    			});
});
//查看题目
$("#bt_lookquestion").click(function(){
	var opsubject = $("#publishquestionsubject option:selected").val();
	  if(opsubject== "zero"){
  	layer.msg('未选择科目！');
  	}else{
  		 table.render({
    elem: '#tb_lookquestion'
    ,url: 'ShowEditQuestionServlet' //数据接口
    ,method: 'post'
    ,even:true
    ,height:590
    ,limits:[6,12]
    ,limit:12
    ,where: {tid: <%=teacherinfo.get(0).getTid()%>, tsubject: opsubject}
    ,page:true
    ,cols: [[
      {field: 'subject', title: '科目', width:150,unresize:true}
      ,{field: 'id', title: '题号', width:80,sort: true,unresize:true}
      ,{field: 'topic', title: '题干', width:350,unresize:true}
      ,{field: 'knowledge', title: '考察点', width:210,unresize:true} 
      ,{field: 'answer', title: '参考答案', width: 350,unresize:true}
      ,{field: 'point', title: '分数', width: 70,unresize:true}
      ,{field: 'right', title: '操作', width: 70,toolbar:'#bar_lookquestion'}
    ]]
  })
  	}
});
//监听行工具事件  查看题目
  table.on('tool(tb_lookquestion)', function(obj){
    var data = obj.data;
	if(obj.event === 'look'){
    	layer.open({
    		type: 2
    		,title: '题目详情'
    		,content: 'LookQuestion.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['关闭']
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_editq');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#subject').val(data.subject);
    			body.find('#id').val(data.id);
    			body.find('#topic').val(data.topic);
    			body.find('#knowledge').val(data.knowledge);
    			body.find('#answer').val(data.answer);
    			body.find('#point').val(data.point);
    		}
    	});
    }
  });
$("#bt_publishquestion").click(function(){
	var begintimevalue = new Date($("#begintime").val()).getTime();
	var endtimevalue = new Date($("#endtime").val()).getTime();
	//获取当前时间
	var myDate = new Date();
	var year = myDate.getFullYear();//年  
	var month = myDate.getMonth() + 1;//月 
	var date = myDate.getDate();//日
	var h = myDate.getHours(); //当前小时(0-23)
	var m = myDate.getMinutes(); //当前分钟(0-59)
	var s = myDate.getSeconds(); //当前秒 
	var nowtime = year + '-' + conver(month) + "-" + conver(date) + " " + conver(h) + ':' + conver(m) + ":" + conver(s);
	function conver(s) {
    	 return s < 10 ? '0' + s : s;
	}
	var begintime = $("#begintime").val();
	var endtime = $("#endtime").val();
	var time = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
   if(begintime.match(time) == null || endtime.match(time) == null){
    	layer.msg('日期输入不合法，请重新输入！');
    }else if(new Date(nowtime).getTime() >= begintimevalue || new Date(nowtime).getTime() >= endtimevalue){
		layer.msg('输入时间小于等于当前时间！');
	}else if(begintimevalue >= endtimevalue){
		layer.msg('开考时间大于等于结束时间！');
	}else{
	    	var opsubject = $("#publishquestionsubject option:selected").val();
  			var opclass = $("#publishquestionclasses option:selected").val();
  			var begintime = $("#begintime").val();
  			var endtime = $("#endtime").val();
  			//先查题表是否为空 和 完整题目的题号是否连续
				$.ajax({
					url:'CheckQtbNullServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': opsubject,
    					'classes': opclass
    				},
    				success:function(res){
    					if(res[0] == "check"){
    					var flag = true;
    					for(var i=1; i<res.length; i++){
    						var zf = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    						var zz = /^\+?[1-9][0-9]*$/;
    						if(!(zf.test(res[i])) && !(zz.test(res[i]))){
    							flag = false;
    							break;
    						}
    					}
    					if(flag == true){
    			$.ajax({
					url:'PublishQServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': opsubject,
    					'classes': opclass,
    					'begintime': begintime,
    					'endtime': endtime
    				},
    				success:function(res){
    				if(res == "success"){
    						layer.msg('发布成功！');
    						$("#bt_lookquestion").click();
  						}
  						else if(res == "error"){
  							layer.msg('发布失败，请重新发布！');
  						}else{
  							  //已经发布过了
  							  laydate.render({
  								  elem: '#begintime'
   									 ,type: 'datetime'
   									 ,value: new Date(res[0].begintime)
  									});   
 								laydate.render({
 								   elem: '#endtime'
 								   ,type: 'datetime'
 								   ,value: new Date(res[0].endtime)
							  });
  							layer.msg('该考试已经发布过，请点击“查看试题”进行查看！');
  						}
    				},
    				error:function(res){
    				}
    			});
    					}else{
    						layer.msg("存在输入分数不合法，请点击“编辑试题”进行检查修改！");
    					}
    					}else{
    						layer.msg(res);
    					}
    				},
    				error:function(res){
    				}
    			});
  	}
  });
//人工评分
$("#bt_beginmark").click(function(){
	  var opsubject = $("#markbyteachersubject option:selected").val();
 		 if(opsubject== "zero"){
  	layer.msg('未选择科目！');
  	}else{
  		var opclass = $("#markbyteacherclasses option:selected").val();
  		  		$.ajax({
					url:'CheckTestStateServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'sclass': opclass,
    					'subject': opsubject
    				},
    				success:function(res){
    					if(res == "考试还未发布！"){
    						layer.msg(res);
    					}else if(res == "考试还未结束！"){
    						layer.msg(res);
    					}else if(res == "canmark"){
    						  	$("#div_markbyteachersubject").css("display", "none");
 						  		 $("#div_markbyteacherclasses").css("display", "none");
 							  	 $("#div_beginmark").css("display", "none");
 							  	 $("#h1_onesubject").text("科目："+opsubject);
 	 						  		$("#h1_oneclass").text("班级："+opclass);
 	 						  		$("#div_markbyteacherhead").css("display", "block");
								 	table.render({
								    elem: '#tb_markbyteacher'
								    ,url: 'ShowQAToTeacherServlet' //数据接口
								    ,even:true
								    ,method: 'get'
								    ,height:581
								    ,limits:[13,26,50,100,200,500]
								    ,limit:13
								    ,where: {subject: opsubject, classes: opclass}
								    ,page:true
								    ,cols: [[
								      {field: 'sid', title: '学号', width:150,sort: true,unresize:true}
								      ,{field: 'id', title: '题号', width:80,sort: true,unresize:true}
								      ,{field: 'point', title: '分值', width: 70,unresize:true}
								      ,{field: 'topic', title: '题目', width:240,unresize:true}
								      ,{field: 'answer', title: '参考答案', width: 300,unresize:true}
								      ,{field: 'myanswer', title: '学生答案', width: 300,unresize:true}
								      ,{field: 'ppoint', title: '打分', width: 70,unresize:true}
								      ,{field: 'right', title: '操作', width: 70,toolbar:'#bar_markbyteacher'}
								    ]]
								  });
 							  	 
  						  	}
    				},
    				error:function(res){
    				}
    			});
    	}
});
  //监听行工具事件  人工评分
  table.on('tool(tb_markbyteacher)', function(obj){
  var opsubject = $("#markbyteachersubject option:selected").val();
  var opclass = $("#markbyteacherclasses option:selected").val();
    var data = obj.data;
	if(obj.event === 'mark'){
    	layer.open({
    		type: 2
    		,title: '评阅题目（人工）'
    		,content: 'MarkByTeacher.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['确定','取消']
    		,yes:function(index, layero){
    			//回调的数值
    			var sid = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_sid").innerText;
    			var id = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_id").innerText;
    			var point = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_point").innerText;
    			var topic = $(layero).find('iframe')[0].contentWindow.document.getElementById("topic").value;
    			var answer = $(layero).find('iframe')[0].contentWindow.document.getElementById("answer").value;
    			var myanswer = $(layero).find('iframe')[0].contentWindow.document.getElementById("myanswer").value;
    			var ppoint = $(layero).find('iframe')[0].contentWindow.document.getElementById("ppoint").value;
    			var zf = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
    			var zz = /^\+?[1-9][0-9]*$/;
    			if(!(zf.test(ppoint)) && !(zz.test(ppoint)) && ppoint != "0"){
    				layer.msg('评分不合法，请重新评分！');
    			}else if(parseInt(ppoint) > parseInt(point) || parseFloat(ppoint) > parseFloat(point)){
    				layer.msg('评分超过题目总分，请重新评分！');
    			}else{
    			  $.ajax({
					url:'StoreAnswerServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'sid': sid,
    					'id': id,
    					'ppoint': ppoint,
    					'subject': opsubject,
    					'sclass': opclass
    				},
    				success:function(res){
    			//同步更新表格数据(非表格重载)
    			obj.update({
    				sid: sid,
    				id: id,
    				point: point,
    				topic: topic,
    				answer: answer,
    				myanswer: myanswer,
    				ppoint: ppoint
    			});
    					layer.msg(res);
    					layer.close(index);
    				},
    				error:function(res){
    					layer.msg('评分提交失败，请重新评分！');
    				}
    			});
    			}
    		}
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_markbyteacher');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#h3_sid').text(data.sid);
    			body.find('#h3_id').text(data.id);
    			body.find('#h3_point').text(data.point);
    			body.find('#topic').val(data.topic);
    			body.find('#answer').val(data.answer);
    			body.find('#myanswer').val(data.myanswer);
    			body.find('#ppoint').val(data.ppoint);
    		}
    	});
    }
  });
//设置人工评分完成标志
$("#bt_markover").click(function(){
	 var opsubject = $("#markbyteachersubject option:selected").val();
 	 var opclass = $("#markbyteacherclasses option:selected").val();
	    	$.ajax({
					url:'AlterTestStateServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'subject': opsubject,
    					'sclass': opclass
    				},
    				success:function(res){
    					if(res == "提交成功，请点击查看成绩单！"){
    						//显示班级成绩单
    						$('#div_markover').css("display", "none");
    						$('#div_looktpestcard').css("display", "block");
    						$('#h1_pagetype').text('成绩单');
    						$('#div_markbyteacher').css("display", "none");
    						$('#div_ptestcard').css("display", "block");
    					}
    					layer.msg(res);
    				},
    				error:function(res){
    				}
    			});
});
//点击查看人工评阅班级成绩单
$("#bt_lookptestcard").click(function(){
	$('#bt_lookptestcard').css("display", "none");
	var opsubject = $("#markbyteachersubject option:selected").val();
 	 var opclass = $("#markbyteacherclasses option:selected").val();
table.render({
    elem: '#tb_ptestcard'
    ,url: 'ShowPTestCard' 
    ,method: 'post'
    ,height:581
    ,where: {subject: opsubject, classes: opclass}
    ,even:true
    ,cols: [[
      {field: 'pranking', title: '排名', width:100,sort: true,unresize:true}
      ,{field: 'sid', title: '学号', width:200,unresize:true}
      ,{field: 'sname', title: '姓名', width: 150,unresize:true}
      ,{field: 'ssex', title: '性别', width:100,unresize:true}
      ,{field: 'ptotalpoint', title: '总分', width: 150,unresize:true}
    ]]
  });
});
//退出评阅(人工)
$("#bt_exitmark").click(function(){
	window.location.reload(true);
});

$("#bt_automark").click(function(){
	var opsubject = $("#markbycomputersubject option:selected").val();
 	if(opsubject== "zero"){
  		layer.msg('未选择科目！');
  	}else{
  		$('#div_markbycomputersubject').css("display", "none");
  		$('#div_automark').css("display", "none");
  			//查询是否已经评阅
  			$.ajax({
					url:'AutoMarkServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'tid': <%=teacherinfo.get(0).getTid()%>,
    					'subject': opsubject
    				},
    				success:function(res){
    					if(res == "考试都还未发布或考试都还未结束，请稍后再进行评阅！"){
    						layer.alert(res, {icon: 2});
    						$('#div_markbycomputersubject').css("display", "block");
  							$('#div_automark').css("display", "block");
    					}
    					//已经评阅过了
    					else if(res == "所有班级都已评阅，请点击查看评阅结果或者成绩单！"){
    						layer.msg(res);
    						$('#div_markbycomputerhead').css("display", "block");
    						$('#div_twosubject').css("display", "block"); //科目
			    			$('#h1_twosubject').text('科目：' + opsubject);
    					}else if(res == "还未评阅完成！"){
  							$('#div_automarkloading').css("display", "block");
  								//自动评阅
  							$.ajax({
								url:'AutoMarkServlet',
			    				type: 'post',
			    				dataType:'json',
			    				data:{
			    					'tid': <%=teacherinfo.get(0).getTid()%>,
			    					'subject': opsubject
			    				},
			    				success:function(res){
			    					layer.msg(res);
			    					if(res == "评阅成功，请点击查看评阅结果或者成绩单！"){
			    						$('#div_automarkloading').css("display", "none");
			    						$('#div_markbycomputerhead').css("display", "block");
			    						$('#div_twosubject').css("display", "block"); //科目
			    						$('#h1_twosubject').text('科目：' + opsubject);
			    					}else{
			    						document.getElementById("markbycomputerarea").style.display = "block";
			    					}
			    				},
			    				error:function(res){
			    				}
			    			});
    					}
    				},
    				error:function(res){
    				}
    			});
  	}
});
//评阅结果（机器）按钮
$("#bt_automarkres").click(function(){
	var opsubject = $("#markbycomputersubject option:selected").val();
	$('#div_pagetype2').css("display", "block");
	$('#h1_pagetype2').text('评阅结果');
	$('#div_ctestcard').css("display", "none");
	$('#div_cmarkresult').css("display", "block"); //评阅结果表格
	table.render({
    elem: '#tb_cmarkresult'
    ,url: 'AutoMarkResultServlet' 
    ,method: 'post'
    ,height:570
    ,where: {tid: <%=teacherinfo.get(0).getTid()%>, subject: opsubject}
    ,even:true
    ,cols: [[
      {field: 'sclass', title: '班级', width:120,sort: true,unresize:true}
      ,{field: 'sid', title: '学号', width:130,sort: true,unresize:true}
      ,{field: 'sname', title: '姓名', width: 90,sort: true,unresize:true}
      ,{field: 'id', title: '题号', width:90,sort: true,unresize:true}
      ,{field: 'topic', title: '题干', width:150,unresize:true}
      ,{field: 'knowledge', title: '考察点', width:140,unresize:true}
      ,{field: 'answer', title: '参考答案', width:160,unresize:true}
      ,{field: 'myanswer', title: '学生作答', width:160,unresize:true}
      ,{field: 'cpoint', title: '机器评分', width:90,unresize:true}
      ,{field: 'point', title: '本题分数', width: 90,unresize:true}
      ,{field: 'right', title: '操作', width: 70,toolbar:'#bar_cmarkresult'}
    ]]
  });
});
  //监听行工具事件  评阅结果（机器）
table.on('tool(tb_cmarkresult)', function(obj){
    var data = obj.data;
	if(obj.event === 'look'){
		layer.open({
    		type: 2
    		,title: '自动评阅结果详情'
    		,content: 'AutoMarkResult.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['关闭']
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_automarkresult');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#h3_sclass').text(data.sclass);
    			body.find('#h3_sid').text(data.sid);
    			body.find('#h3_sname').text(data.sname);
    			body.find('#h3_id').text(data.id);
    			body.find('#h3_cpoint').text(data.cpoint);
    			body.find('#h3_point').text(data.point);
    			body.find('#topic').val(data.topic);
    			body.find('#knowledge').val(data.knowledge);
    			body.find('#answer').val(data.answer);
    			body.find('#myanswer').val(data.myanswer);
    		}
    	});
	}
});
//成绩单（机器）按钮
$("#bt_looktestcard2").click(function(){
	var opsubject = $("#markbycomputersubject option:selected").val();
	$('#div_pagetype2').css("display", "block");
	$('#h1_pagetype2').text('总成绩单');
	$('#div_cmarkresult').css("display", "none");
	$('#div_ctestcard').css("display", "block"); //成绩单表格
table.render({
    elem: '#tb_ctestcard'
    ,url: 'AutoMarkResultServlet' 
    ,method: 'get'
    ,height: 570
    ,where: {tid: <%=teacherinfo.get(0).getTid()%>, subject: opsubject}
    ,even:true
    ,cols: [[
   		{field: 'cranking', title: '总排名', width:130,sort: true,unresize:true}
   	,{field: 'pranking', title: '班排名', width:130,sort: true,unresize:true}
      ,{field: 'sclass', title: '班级', width:200,sort: true,unresize:true}
      ,{field: 'sid', title: '学号', width:200,unresize:true}
      ,{field: 'sname', title: '姓名', width: 150,unresize:true}
      ,{field: 'ssex', title: '性别', width:100,unresize:true}
      ,{field: 'ctotalpoint', title: '总分', width: 150,unresize:true}
    ]]
  });
});
//退出评阅(机器)
$("#bt_exitmark2").click(function(){
	window.location.reload(true);
});
//评分对比
$("#markcompare").click(function(){
    						document.getElementById("teacherlistarea").style.display = "none";
						   document.getElementById("testlistarea").style.display = "none";
						  document.getElementById("tudtpwdarea").style.display = "none";
						  document.getElementById("tudspwdarea").style.display = "none";
						  document.getElementById("tregarea").style.display = "none";
						  document.getElementById("sviewtbarea").style.display = "none";
						  document.getElementById("editquestionarea").style.display = "none";
						  document.getElementById("publishquestionarea").style.display = "none";
						  document.getElementById("markbyteacherarea").style.display = "none";
						  document.getElementById("markbycomputerarea").style.display = "none";
  	    	$.ajax({
					url:'MarkAnalysisServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'classname': '网络17k1',
    					'subject': '思修'
    				},
    				success:function(res){
    					if(res == "【网络17k1 思修】老师或机器还未批改完毕，请稍后再查看！"){
    						layer.alert(res, {icon: 2});
    					}else{
    						document.getElementById("markcomparearea").style.display = "block";
						  var x = new Array(); //题号
						  var yp = new Array(); //老师评分
						  var yc = new Array(); //机器评分
						  for(var i=0; i<res.length; i++){
						  	x[i] = '第' + res[i].id + '题';
						  	yp[i] = res[i].pavgpoint;
						  	yc[i] = res[i].cavgpoint;
						  }
						// 基于准备好的dom，初始化echarts实例
				        var myChart = echarts.init(document.getElementById('ECharts'));
				        var option = {
						    tooltip: {
						        trigger: 'axis'
						    },
						    legend: {
						        data: ['教师评分', '机器评分']
						    },
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '3%',
						        containLabel: true
						    },
						    xAxis: {
						        type: 'category',
						        name: '题号',
						        boundaryGap: false,
						        data: x
						    },
						    yAxis: {
						        type: 'value',
						        name: '分值'
						    },
						    series: [
						        {
						            name: '教师评分',
						            type: 'line',
						            data: yp
						        },
						        {
						            name: '机器评分',
						            type: 'line',
						            data: yc
						        }
						    ]
						};
				
				        // 使用刚指定的配置项和数据显示图表。
				        myChart.setOption(option);
    					}
    				},
    				error:function(res){
    				}
    			});
});
  <% //数据库类错误，返回并刷新主页面
          String error=(String)request.getAttribute("error");
          if(error!=null){
          %>
    		layer.alert('<%=error%>', {icon: 2});
      <%}%>
      <%//老师修改密码成功
          String result_tuppwd = (String)request.getAttribute("result_tuppwd");
          if(result_tuppwd != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/thome.jsp');
          document.getElementById("sidenavli2").click();
        document.getElementById("tudtpwdonclick").click();
    		layer.alert('<%=result_tuppwd%>', {icon: 1});
      <%}%>
      <%//老师修改学生密码但查无此人
          String error_nostudent=(String)request.getAttribute("error_nostudent");
          if(error_nostudent!=null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/thome.jsp');
          document.getElementById("sidenavli3").click();
        document.getElementById("tudspwdonclick").click();
    		layer.alert('<%=error_nostudent%>', {icon: 2});
      <%}%>
      <%//老师修改学生密码成功
          String result_tsuppwd = (String)request.getAttribute("result_tsuppwd");
          if(result_tsuppwd != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/thome.jsp');
          document.getElementById("sidenavli3").click();
        document.getElementById("tudspwdonclick").click();
    	layer.alert('<%=result_tsuppwd%>', {icon: 1});
      <%}%>
      <%//老师注册学生成功
          String result_studentregister = (String)request.getAttribute("result_studentregister");
          if(result_studentregister != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/thome.jsp');
          document.getElementById("sidenavli3").click();
        document.getElementById("tregclick").click();
    	layer.alert('<%=result_studentregister%>', {icon: 1});
      <%}%>
      <% //班级无无学生
          String error_classnostudent = (String)request.getAttribute("error_classnostudent");
          if(error_classnostudent != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/thome.jsp');
          document.getElementById("sidenavli3").click();
        document.getElementById("sviewtbclick").click();
    	layer.alert('<%=error_classnostudent%>', {icon: 2});
      <%}%>
      <%//显示班级学生信息表的名称
          String classinfotbtitle = (String)request.getAttribute("classinfotbtitle");
          if(classinfotbtitle != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/thome.jsp');
          document.getElementById("sidenavli3").click();
        document.getElementById("sviewtbclick").click();
        document.getElementById("classtbtitle").style.display = "block";
        document.getElementById("tbsview").style.display = "block";
        document.getElementById("sbh2").innerHTML = "<%=classinfotbtitle%>班学生信息表";
      <%}%>
  });
  function exit(){
  window.location="login.jsp"
  }
  function pwdshow(){
   	document.getElementById("inputnewpwd1").type = "text";
   	document.getElementById("inputnewpwd2").type = "text";
   }
   function pwdhidden(){
   	document.getElementById("inputnewpwd1").type = "password";
   	document.getElementById("inputnewpwd2").type = "password";
   }
   function uptpwd(){
   	<%
   		session.setAttribute("tid",teacherinfo.get(0).getTid());
   	%>
   }
   function showclassinfotb(){
   document.getElementById("classtbtitle").style.display = "block";
   document.getElementById("tbsview").style.display = "block";
   }
   function teacherlist() {
  document.getElementById("teacherlistarea").style.display = "block";
   document.getElementById("testlistarea").style.display = "none";
  document.getElementById("tudtpwdarea").style.display = "none";
  document.getElementById("tudspwdarea").style.display = "none";
  document.getElementById("tregarea").style.display = "none";
  document.getElementById("sviewtbarea").style.display = "none";
  document.getElementById("editquestionarea").style.display = "none";
  document.getElementById("publishquestionarea").style.display = "none";
  document.getElementById("markbyteacherarea").style.display = "none";
  document.getElementById("markbycomputerarea").style.display = "none";
  document.getElementById("markcomparearea").style.display = "none";
}
   function tudtpwd() {
  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "block";
    document.getElementById("tudspwdarea").style.display = "none";
    document.getElementById("tregarea").style.display = "none";
    document.getElementById("sviewtbarea").style.display = "none";
    document.getElementById("editquestionarea").style.display = "none";
    document.getElementById("publishquestionarea").style.display = "none";
    document.getElementById("markbyteacherarea").style.display = "none";
    document.getElementById("markbycomputerarea").style.display = "none";
    document.getElementById("markcomparearea").style.display = "none";
}
	function tudspwd() {
  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "none";
   document.getElementById("tudspwdarea").style.display = "block";
   document.getElementById("tregarea").style.display = "none";
   document.getElementById("sviewtbarea").style.display = "none";
   document.getElementById("editquestionarea").style.display = "none";
   document.getElementById("publishquestionarea").style.display = "none";
   document.getElementById("markbyteacherarea").style.display = "none";
   document.getElementById("markbycomputerarea").style.display = "none";
   document.getElementById("markcomparearea").style.display = "none";
}
function treg() {
  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "none";
   document.getElementById("tudspwdarea").style.display = "none";
   document.getElementById("tregarea").style.display = "block";
   document.getElementById("sviewtbarea").style.display = "none";
   document.getElementById("editquestionarea").style.display = "none";
   document.getElementById("publishquestionarea").style.display = "none";
   document.getElementById("markbyteacherarea").style.display = "none";
   document.getElementById("markbycomputerarea").style.display = "none";
   document.getElementById("markcomparearea").style.display = "none";
}
function sviewtb() {
  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "none";
   document.getElementById("tudspwdarea").style.display = "none";
   document.getElementById("tregarea").style.display = "none";
   document.getElementById("sviewtbarea").style.display = "block";
   document.getElementById("editquestionarea").style.display = "none";
   document.getElementById("publishquestionarea").style.display = "none";
   document.getElementById("markbyteacherarea").style.display = "none";
   document.getElementById("markbycomputerarea").style.display = "none";
   document.getElementById("markcomparearea").style.display = "none";
   
}
function editquestion() {
  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "none";
   document.getElementById("tudspwdarea").style.display = "none";
   document.getElementById("tregarea").style.display = "none";
   document.getElementById("sviewtbarea").style.display = "none";
   document.getElementById("editquestionarea").style.display = "block";
   document.getElementById("publishquestionarea").style.display = "none";
   document.getElementById("markbyteacherarea").style.display = "none";
   document.getElementById("markbycomputerarea").style.display = "none";
   document.getElementById("markcomparearea").style.display = "none";
}
function publishquestion() {
  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "none";
   document.getElementById("tudspwdarea").style.display = "none";
   document.getElementById("tregarea").style.display = "none";
   document.getElementById("sviewtbarea").style.display = "none";
   document.getElementById("editquestionarea").style.display = "none";
   document.getElementById("publishquestionarea").style.display = "block";
   document.getElementById("markbyteacherarea").style.display = "none";
   document.getElementById("markbycomputerarea").style.display = "none";
   document.getElementById("markcomparearea").style.display = "none";
}
function markbyteacher() {
  document.getElementById("teacherlistarea").style.display = "none";
   document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "none";
   document.getElementById("tudspwdarea").style.display = "none";
   document.getElementById("tregarea").style.display = "none";
   document.getElementById("sviewtbarea").style.display = "none";
   document.getElementById("editquestionarea").style.display = "none";
   document.getElementById("publishquestionarea").style.display = "none";
	document.getElementById("markbyteacherarea").style.display = "block";
	document.getElementById("markbycomputerarea").style.display = "none";
	document.getElementById("markcomparearea").style.display = "none";
}
function markbycomputer() {
  document.getElementById("teacherlistarea").style.display = "none";
  document.getElementById("testlistarea").style.display = "none";
   document.getElementById("tudtpwdarea").style.display = "none";
   document.getElementById("tudspwdarea").style.display = "none";
   document.getElementById("tregarea").style.display = "none";
   document.getElementById("sviewtbarea").style.display = "none";
   document.getElementById("editquestionarea").style.display = "none";
   document.getElementById("publishquestionarea").style.display = "none";
	document.getElementById("markbyteacherarea").style.display = "none";
document.getElementById("markbycomputerarea").style.display = "block";
document.getElementById("markcomparearea").style.display = "none";
}
    </script>
  </body>
</html>