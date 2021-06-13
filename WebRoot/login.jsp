<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>

  </head>
  
  <body>
		<div id="head" style="
			position:absolute; 
		  	left:0; top:0; 
		  	width:100%;
		  	height:100px; 
  	 background-image:url('image/login_head.jpg');
              	background-size: cover;
		  	background-position:center;"></div>
		  	
		  	<div class="layui-carousel" id="login_bg" style="
		  		position: absolute;
		  		top: 100px;left:0;">
  <div carousel-item>
  	<div><img src="image/login_bg1.jpg" width="100%" height="100%"></div>
    <div><img src="image/login_bg2.jpg" width="100%" height="100%"></div>
    <div><img src="image/login_bg3.jpg" width="100%" height="100%"></div>
  </div>
</div>
		  	
		  	<div id="loginarea" style="
        width: 300px;
		height: 300px;
		position: absolute;
		right: 60px;
		top: 150px;
  	  background-color: white">
  	  
  	  	  	  <div id="title" style="
  	  	  	  	position: absolute;
  	  	  	  	width: 200px;
  	  	  	  	height: 65px;
  	  	  	  	left: 50px;
  	  	  	  	top: 5px;
  	  	background-image:url('image/login_formtitle.png');
              	background-size: cover;
		  	background-position:center;"></div>
  	  	
  	  <div id="idicon" style="
  	  	position:absolute;
  	  	height: 35px;
  	  	width: 35px;
  	  	left: 50px;
  	  top: 80px;
  	  background-color: #F0F0F0;">
    <i class="layui-icon layui-icon-user" style="font-size: 30px; color: gray;"></i></div>
    	
          
  <form class="layui-form" action="LoginServlet" method="post">
  <div class="layui-form-item" style="
  	position:absolute;
  	height: 35px;
  	width: 165px;
  	left: 85px;
  	  top: 80px;">
    <div class="layui-input-block" style="
    	position:absolute;
  	right: 0;
  	width: 165px;">
     <input type="text" name="id" required  lay-verify="required" placeholder="学工号" autocomplete="off" class="layui-input"></div></div>
  	 
  	 <div id="pwdicon" style="
  	  	position:absolute;
  	  	height: 35px;
  	  	width: 35px;
  	  	left: 50px;
  	  top: 130px;
  	  background-color:#F0F0F0;">
    <i class="layui-icon layui-icon-password" style="font-size: 30px; color: gray;"></i></div>
    
  <div class="layui-form-item" style="
  	position:absolute;
  	height: 35px;
  	width: 165px;
  	left: 85px;
  	  top: 130px;">
    <div class="layui-input-block" style="
    	position:absolute;
  	right: 0;
  	width: 165px;">
     <input type="password" name="pwd" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input"></div></div>
     
      <div id="pwdicon" style="
  	  	position:absolute;
  	  	height: 35px;
  	  	width: 35px;
  	  	left: 50px;
  	  top: 185px;
  	  background-color:#F0F0F0;">
    <i class="layui-icon layui-icon-friends" style="font-size: 30px; color: gray;"></i></div>
    
    <div class="layui-form-item" style="
    	position:absolute;
  	height: 35px;
  	width: 160px;
  	left: 90px;
  	  top: 185px;">
    <div class="layui-input-block" style="
    	position:absolute;
  	right: 0;
  	width: 165px;">
  	<select name="character" lay-verify="required">
        <option value="student">学生</option>
        <option value="teacher">教师</option>
        <option value="admin">管理员</option>
      </select></div></div>
     
 <div class="layui-form-item" style="
     	position:absolute;
  	height: 35px;
  	width: 200px;
  	left: 50px;
  	  top: 240px;">
    <div class="layui-input-block">
      <button class="layui-btn" type="submit" lay-submit lay-filter="login" style="position:absolute;right: 0;width: 200px;">登录</button></div></div>
  	  
  	 </form>
  	 <script type="text/javascript" charset="utf-8" src="layui/layui.js" ></script>
  	 <script type="text/javascript">
  	 	layui.use(['form','layer','carousel'],function(){
  	 		var form = layui.form, layer= layui.layer, carousel = layui.carousel;
  	 		
		//图片轮播
  carousel.render({
    elem: '#login_bg', 
	width:'100%',
	height:'88%',
	interval: 2000
  });
  	 		
    	<%
          String error=(String)request.getAttribute("error");
          if(error!=null){
          %>
    		layer.alert('<%=error%>', {icon: 2},function(yes){
    		window.location="login.jsp"
    		layer.close(yes);});
      <%}%>
     });
     
  	 </script>
  	 
  	</div>
	</body>
</html>