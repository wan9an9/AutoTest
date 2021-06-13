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
    
    <title>学生端</title>
    
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
		  	height:60px; ">
		  	<div id="sysname" style="
		  		position:absolute; 
		  	left:0; top:0; 
		  	width:200px;
		  	height:60px;
		  	z-index: 999;
		  	text-align: center;">
		  	<h1 style="color: white; font-style:italic;">主观题评分系统</h1>
			<h3 style="color: yellow;">管理员端</h3>
		</div>
		
<ul class="layui-nav  layui-bg-black"  lay-filter="headnav" style="text-align: right;border-radius: 0;">
  <li class="layui-nav-item" style="height:60px;">
    <a><img src="image/touxiang.jpg" class="layui-nav-img"  style="height:60px;width: 60px;"></a>
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
	        AdminBean admin = (AdminBean)request.getSession().getAttribute("session_admin");
	        %>
		  	<h3 style="color: white"><%=admin.getAid()%></h3>
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
    <a href="javascript:;" id="sidenavli1">信息列表</a>
    <dl class="layui-nav-child">
      <dd><a onclick="teacherlist()" id="teacherlistonclick" class="layui-this">教师列表</a></dd>
      <dd><a id="qalistonclick">题库列表</a></dd>
      <dd><a id="testonclick">考试列表</a></dd>
    </dl>
  	</li>
  	
  	<li class="layui-nav-item">
    <a onclick="updatepwd()" id="sidenavli2">修改密码</a>
  	</li>
	
    <li class="layui-nav-item">
    <a onclick="teacherinfo()" id="sidenavli3">教师信息管理</a>
  	</li>
  
  <li class="layui-nav-item">
    <a onclick="classbankinfo()" id="sidenavli4">题库表管理</a>
  </li>
</ul>
	</div>
	
<div id="teacherlistarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;">
<div style="position:absolute; left: 10px; top:10px;">
<table class="layui-hide" id="tb_teacherinfo" lay-filter="tb_teacherinfo"></table>
</div>
</div>

<div id="qalistarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<div style="position:absolute; left: 10px; top:10px;">
<table class="layui-hide" id="tb_classbankinfo" lay-filter="tb_classbankinfo"></table>
</div>
</div>

<div id="testlistarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<div style="position:absolute; left: 10px; top:10px;">
<table class="layui-hide" id="tb_testinfo"></table>
</div>
</div>
		  		
<div id="updatepwdarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
	<div id="updatepwddiv" style="
		  		position:absolute; 
		  	left: 0; top:0;">
<form class="layui-form" action="UpdatePwdServlet" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">
    	<i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #009688;" onmousedown="pwdshow()" onmouseup="pwdhidden()"></i>
    </label>
    <div class="layui-input-block">
      <input type="password" id="inputnewpwd1" name="anewpassword" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
      <button type="submit" class="layui-btn" lay-submit style="position:absolute; top: 0; margin-left: 220px ;" onclick="uppwd">修改</button>
    </div>
  </div>
  </form>
	</div></div>
	
<div id="teacherinfoarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<div id="regform" style="
		  		position:absolute; width: 400px;
		  	left: 0; top:0;">
<form class="layui-form" action="TRegisterServlet" method="post">
<div class="layui-form-item">
    <div class="layui-form-mid layui-word-aux" style="left: 120px;"><h2 style="color: black;">注册教师</h2></div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">工号</label>
    <div class="layui-input-block">
      <input type="text" name="regtid" required  lay-verify="required" placeholder="请输入工号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-form-mid layui-word-aux" style="left: 120px;"><h4 style="color: red;">* 初始密码和工号相同。</h4></div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block">
      <input type="text" name="regtname" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="regtsex" value="男" title="男" checked>
      <input type="radio" name="regtsex" value="女" title="女" >
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">班级</label>
    <div class="layui-input-block">
    <textarea name="regtclasses" required lay-verify="required" placeholder="输入时以”,“分隔" class="layui-textarea"></textarea>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">教授科目</label>
    <div class="layui-input-block">
      <input type="text" name="regtsubjects" required lay-verify="required" placeholder="请输入教授科目" class="layui-input">
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
	
<div id="updateclassbankarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
<div id="insertclassbank" style="
		  		position:absolute; 
		  	left: 0; top:0;">
<div class="layui-form-item">
    <div class="layui-form-mid layui-word-aux" style="left: 120px;"><h2 style="color: black;">新增库</h2></div>
</div>
<form class="layui-form" action="AddLibraryServlet" method="post">
<div class="layui-form-item" >
    <label class="layui-form-label">班级名称</label>
    <div class="layui-input-block">
      <input type="text" name="classname" required  lay-verify="required" placeholder="请输入班级名称" autocomplete="off" class="layui-input">
    </div>
</div>
<div class="layui-form-item" >
    <label class="layui-form-label">题库表名</label>
    <div class="layui-input-block">
      <input type="text" name="qbankdbname" required  lay-verify="required" placeholder="请输入库表名称：qbank_*" autocomplete="off" class="layui-input">
    </div>
</div>
<div class="layui-form-item" >
    <label class="layui-form-label">作答表名</label>
    <div class="layui-input-block">
      <input type="text" name="abankdbname" required  lay-verify="required" placeholder="请输入库表名称：abank_*" autocomplete="off" class="layui-input">
    </div>
</div>
<div class="layui-form-item">
    <div class="layui-input-block">
      <button type="submit"class="layui-btn" lay-submit lay-filter="formDemo">添加</button>
      <button type="reset" class="layui-btn layui-btn-pridimary">重置</button>
    </div>
</div>
</form>
</div>

</div>


<script type="text/html" id="bar_teacherinfo">
  <a class="layui-btn layui-btn-xs" lay-event="alter">修改班级</a>
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">确定删除</a>
</script> 
<script type="text/html" id="bar_dellib">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">确定删除</a>
</script>  	
<script type="text/javascript" charset="utf-8" src="layui/layui.js" ></script>
<script type="text/javascript">
        layui.use(['form','layer','carousel','table','jquery','laydate'],function(){
  	 		var form = layui.form;
  	 		var layer= layui.layer;
  	 		var carousel = layui.carousel;
  	 		var table = layui.table; 
  	 		var $ = layui.$;
  	 		var laydate = layui.laydate;
  	 		
window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');

//首页显示教师表
	table.render({
    elem: '#tb_teacherinfo'
    ,url: 'TShowInfoServlet' //数据接口
    ,even:true
    ,height:590
    ,limits:[12,20,50,100,200,500]
    ,limit:12
    ,page:true
    ,cols: [[
      {field: 'tid', title: '工号', width:150,sort: true,unresize:true}
      ,{field: 'tname', title: '姓名', width:100,sort: true,unresize:true}
      ,{field: 'tsex', title: '性别', width:70,unresize:true}
      ,{field: 'tclasses', title: '所教班级', width:400,unresize:true} 
      ,{field: 'tsubjects', title: '教授科目', width: 400,unresize:true}
      ,{field: 'right', title: '操作', width:180,toolbar:'#bar_teacherinfo'}
    ]]
  });
  //监听行工具事件  教师表操作
  table.on('tool(tb_teacherinfo)', function(obj){
  	 var data = obj.data;
  	 if(obj.event === 'alter'){
  	    layer.open({
    		type: 2
    		,title: '修改老师所教班级'
    		,content: 'AlterTeacherClasses.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['确定','取消']
    		,yes:function(index, layero){
    			//回调的数值
    			var tid = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_tid").innerText;
    			var tname = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_tname").innerText;
    			var tsex = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_tsex").innerText;
    			var tclasses = $(layero).find('iframe')[0].contentWindow.document.getElementById("tclasses").value;
    			var tsubjects = $(layero).find('iframe')[0].contentWindow.document.getElementById("tsubjects").value;
    			if(tclasses != "" && tclasses != null){
    			$.ajax({
					url:'UpdateClassServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'tclasses': tclasses
    				},
    				success:function(res){
	    				if(res == "success"){
		    				$.ajax({
							url:'UpdateClassServlet',
		    				type: 'post',
		    				dataType:'json',
		    				data:{
		    					'tid': tid,
		    					'tclasses': tclasses,
		    					'tsubjects': tsubjects
		    				},
		    				success:function(res){
		    				if(res == "success"){
				    			//同步更新表格数据(非表格重载)
				    			obj.update({
				    				tid: tid,
				    				tname: tname,
				    				tsex: tsex,
				    				tclasses: tclasses,
				    				tsubjects: tsubjects
				    			});
		    					layer.msg('修改班级成功！');
		    					layer.close(index);
		    					}else if(res == "error"){
		    						layer.msg('修改班级失败，请重试！');
		    					}
		    				},
		    				error:function(res){
		    					layer.msg('修改班级失败，请重试！');
		    				}
		    			});
	    					}else{
	    						layer.msg('【'+res+'】不存在，请重新修改班级！');
	    					}
    				},
    				error:function(res){
    					layer.msg('修改班级失败，请重试！');
    				}
    			});
    			}
    		}
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_alterteacherinfo');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#h3_tid').text(data.tid);
    			body.find('#h3_tname').text(data.tname);
    			body.find('#h3_tsex').text(data.tsex);
    			body.find('#tsubjects').val(data.tsubjects);
    			body.find('#tclasses').val(data.tclasses);
    		}
    	});
	}else if(obj.event === 'del'){
				$.ajax({
					url:'DeleteLibraryServlet',
    				type: 'get',
    				dataType:'json',
    				data:{
    					'tid': data.tid,
    					'subject': data.tsubjects
    				},
    				success:function(res){
    				if(res == "success"){
    					layer.alert('删除成功！', {icon: 1});
    					window.location="admin.jsp";
    					}else if(res == "error"){
    						layer.msg('删除失败，请重试！');
    					}
    				},
    				error:function(res){
    					layer.msg('删除失败，请重试！');
    				}
    			});
	}
});
//题库表
$("#qalistonclick").click(function(){
  document.getElementById("teacherlistarea").style.display = "none";
  document.getElementById("qalistarea").style.display = "block";
  document.getElementById("testlistarea").style.display = "none";
  document.getElementById("updatepwdarea").style.display = "none";
  document.getElementById("teacherinfoarea").style.display = "none";
  document.getElementById("updateclassbankarea").style.display = "none";
		table.render({
    elem: '#tb_classbankinfo'
    ,url: 'CShowInfoServlet' //数据接口
    ,even:true
    ,height:590
    ,limits:[12,20,50,100,200,500]
    ,limit:12
    ,page:true
    ,cols: [[
      {field: 'classname', title: '班级', width:300,unresize:true}
      ,{field: 'qbankdbname', title: '题库表名', width:400,unresize:true}
      ,{field: 'abankdbname', title: '作答表名', width:400,unresize:true}
      ,{field: 'right', title: '操作', width:100,toolbar:'#bar_dellib'}
    ]]
  });
});
  //监听行工具事件  删除库
  table.on('tool(tb_classbankinfo)', function(obj){
  	    var data = obj.data;
	if(obj.event === 'del'){
		$.ajax({
					url:'DeleteLibraryServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'classname': data.classname,
    				},
    				success:function(res){
    				if(res == "success"){
    				layer.msg('删除成功！');
    					}else if(res == "error"){
    						layer.msg('删除失败，请重试！');
    					}
    				},
    				error:function(res){
    					layer.msg('删除失败，请重试！');
    				}
    			});
	}
});
//考试信息表
$("#testonclick").click(function(){
document.getElementById("teacherlistarea").style.display = "none";
  document.getElementById("qalistarea").style.display = "none";
  document.getElementById("testlistarea").style.display = "block";
  document.getElementById("updatepwdarea").style.display = "none";
  document.getElementById("teacherinfoarea").style.display = "none";
  document.getElementById("updateclassbankarea").style.display = "none";
  		table.render({
    elem: '#tb_testinfo'
    ,url: 'CShowInfoServlet' //数据接口
    ,method: 'post'
    ,even:true
    ,height:590
    ,limits:[12,20,50,100,200,500]
    ,limit:12
    ,page:true
    ,cols: [[
      {field: 'classname', title: '班级', width:300,sort: true,unresize:true}
      ,{field: 'subject', title: '科目', width:300,sort: true,unresize:true}
      ,{field: 'begintime', title: '开考时间', width:250,unresize:true}
      ,{field: 'endtime', title: '结束时间', width:250,unresize:true}
      ,{field: 'ccheckover', title: '机器批改', width:100,unresize:true}
      ,{field: 'pcheckover', title: '教师批改', width:100,unresize:true}
    ]]
  });
});

  <% //数据库类错误，返回并刷新主页面
          String error=(String)request.getAttribute("error");
          if(error!=null){
          %>
    		layer.alert('<%=error%>', {icon: 2});
      <%}%>
		<% //修改密码成功
          String result_auppwd = (String)request.getAttribute("result_auppwd");
          if(result_auppwd != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli2").click();
    		layer.alert('<%=result_auppwd%>', {icon: 1});
      <%}%>
      <% //教授该科目的该教师已注册
          String error_oldteacher = (String)request.getAttribute("error_oldteacher");
          if(error_oldteacher != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli3").click();
    	layer.alert('<%=error_oldteacher%>', {icon: 2});
      <%}%>
      <% //老师注册成功
          String result_teacherregister = (String)request.getAttribute("result_teacherregister");
          if(result_teacherregister != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli3").click();
    	layer.alert('<%=result_teacherregister%>', {icon: 1});
      <%}%>
      <% //暂无教师
          String error_noteacher = (String)request.getAttribute("error_noteacher");
          if(error_noteacher != null){
          %>
    	layer.msg(error_noteacher);
      <%}%>
      <% //暂无班级
          String error_noclass = (String)request.getAttribute("error_noclass");
          if(error_noclass != null){
          %>
    	layer.msg(error_noclass);
      <%}%>
      <% //不存在教授该科目的该教师！
          String error_teacher = (String)request.getAttribute("error_teacher");
          if(error_teacher != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli3").click();
    	layer.alert('<%=error_teacher%>', {icon: 2});
      <%}%>
      <% //该班级已存在
          String result_havethisclass = (String)request.getAttribute("result_havethisclass");
          if(result_havethisclass != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli4").click();
    	layer.alert('<%=result_havethisclass%>', {icon: 2});
      <%}%>
      <% //无考试
          String error_notest = (String)request.getAttribute("error_notest");
          if(error_notest != null){
          %>
    	layer.msg(error_notest);
      <%}%>
      <% //题目库表名称重复
          String SameQBankDbName = (String)request.getAttribute("SameQBankDbName");
          if(SameQBankDbName != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli4").click();
    	layer.alert('<%=SameQBankDbName%>', {icon: 2});
      <%}%>
      <% //作答库表名称重复
          String SameABankDbName = (String)request.getAttribute("SameABankDbName");
          if(SameABankDbName != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli4").click();
    	layer.alert('<%=SameABankDbName%>', {icon: 2});
      <%}%>
      <% //添加班级成功
          String result_addclass = (String)request.getAttribute("result_addclass");
          if(result_addclass != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/admin.jsp');
          document.getElementById("sidenavli4").click();
    	layer.alert('<%=result_addclass%>', {icon: 1});
      <%}%>
   });
   
   function teacherlist() {
  document.getElementById("teacherlistarea").style.display = "block";
  document.getElementById("qalistarea").style.display = "none";
  document.getElementById("testlistarea").style.display = "none";
  document.getElementById("updatepwdarea").style.display = "none";
  document.getElementById("teacherinfoarea").style.display = "none";
  document.getElementById("updateclassbankarea").style.display = "none";
}
function updatepwd() {
  document.getElementById("teacherlistarea").style.display = "none";
  document.getElementById("qalistarea").style.display = "none";
  document.getElementById("testlistarea").style.display = "none";
  document.getElementById("updatepwdarea").style.display = "block";
    document.getElementById("teacherinfoarea").style.display = "none";
    document.getElementById("updateclassbankarea").style.display = "none";
}
function teacherinfo() {
  document.getElementById("teacherlistarea").style.display = "none";
  document.getElementById("qalistarea").style.display = "none";
  document.getElementById("testlistarea").style.display = "none";
  document.getElementById("updatepwdarea").style.display = "none";
    document.getElementById("teacherinfoarea").style.display = "block";
    document.getElementById("updateclassbankarea").style.display = "none";
}
function classbankinfo() {
  document.getElementById("teacherlistarea").style.display = "none";
  document.getElementById("qalistarea").style.display = "none";
  document.getElementById("testlistarea").style.display = "none";
  document.getElementById("updatepwdarea").style.display = "none";
    document.getElementById("teacherinfoarea").style.display = "none";
document.getElementById("updateclassbankarea").style.display = "block";
}
function exit(){
  window.location="login.jsp"
  }
  function pwdshow(){
   	document.getElementById("inputnewpwd1").type = "text";
   }
   function pwdhidden(){
   	document.getElementById("inputnewpwd1").type = "password";
   }
   function uppwd(){
   	<%
   		session.setAttribute("aid",admin.getAid());
   	%>
   }
    </script>
  </body>
</html>
