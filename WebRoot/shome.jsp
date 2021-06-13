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
		  	<h1 style="color: white; font-style:italic;">主观题评分系统</h2>
			<h3 style="color: yellow;">学生端</h3>
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
	        StudentBean studentinfo = (StudentBean)request.getSession().getAttribute("session_student");
	        %>
		  	<h3 style="color: white"><%=studentinfo.getSname()%></h3>
		  	<h3 style="color: white"><%=studentinfo.getSid()%></h3>
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
	
	<li class="layui-nav-item layui-this" id="sidenavli1">
    <a onclick="home()">首页</a>
  </li>
	
    <li class="layui-nav-item">
    <a href="javascript:;" id="sidenavli2">我的信息</a>
    <dl class="layui-nav-child">
      <dd><a onclick="udpwd()" id="sudspwdonclick">修改密码</a></dd>
    </dl>
  </li>
  
  <li class="layui-nav-item">
    <a href="javascript:;" id="sidenavli3">考试信息</a>
    <dl class="layui-nav-child">
      <dd><a onclick="begintest()" id="begintestonclick">开始考试</a></dd>
      <dd><a onclick="lookscore()" id="lookscoreonclick">查询成绩</a></dd>
    </dl>
  </li>
  
</ul>
	</div>
	
	<div id="homearea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;">
		  	<div id="homediv" style="
		  		position:absolute; 
		  	left: 100px; top:0;">
		  		<table class="layui-hide" id="tb_studentinfo"></table></div></div>
		  		
<div id="udpwdarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display: none;">
		  	<div id="pwdform" style="
		  		position:absolute; 
		  	left: 0; top:0;">
<form class="layui-form" action="UpdatePwdServlet" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">
    	<i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #009688;" onmousedown="pwdshow()" onmouseup="pwdhidden()"></i>
    </label>
    <div class="layui-input-block">
      <input type="password" id="inputnewpwd" name="snewpassword" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
      <button type="submit" class="layui-btn" lay-submit lay-filter="updatepwd" style="margin-top: 10px; margin-left: 40px ;" onclick="uppwd">修改</button>
    </div>
  </div>
  </form>
  </div>
</div>

<div id="testarea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display:none;">
<form class="layui-form">
<div class="layui-form-item" id="div_selectsubject">
    <label class="layui-form-label">科目</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="testsubject" id="testsubject">
          <option value="zero">请选择科目</option>
      <%
      	 String [] mysubjects = studentinfo.getSsubjects().split(",|，| ");
      	 for(int k=0; k<mysubjects.length; k++)
      	{
       %>
        <option value="<%=mysubjects[k]%>"><%=mysubjects[k]%></option>
        <%} %>
      </select>
    </div>
</div>
 <div class="layui-form-item" id="div_begintest" style="position:absolute;top: 0;left:350px;">
      <button type="button"class="layui-btn" id="bt_begintest">开始考试</button>
 </div>
<div id="div_testhead" style="display: none;position:absolute; height: 30px; left:33px;top: 0; width: 1250px;background-color: #a4cab6;">
<div class="layui-form-item" style="position:absolute;left:25px;width: 500px;height: 30px;">
    <h2 id="h2_subject"></h2>
</div>
<div class="layui-form-item" style="position:absolute;left:525px;width: 175px;height: 30px;">
    <h2 id="h2_totalpoints"></h2>
</div>
<div class="layui-form-item" style="position:absolute;left:700px;width: 550px;height: 30px;">
    <h2 id="h2_testtime"></h2>
</div>
</div>
 	</form>
<div id="div_test" style="
		  		position:absolute; 
		  	left: 10px; top:40px;">
		  	<table class="layui-hide" id="tb_test" lay-filter="tb_test"></table>
</div>
</div>

<div id="lookscorearea" style="
	position:absolute; 
		  	right: 10px; top:70px; 
		  	width: 86%;
		  	height:89%;
		  	display:none;">
<form class="layui-form">
<div class="layui-form-item" id="div_selectsubject2">
    <label class="layui-form-label">科目</label>
    <div class="layui-input-block" style="width: 200px;">
      <select name="testsubject2" id="testsubject2">
          <option value="zero">请选择科目</option>
      <%
      	 for(int k=0; k<mysubjects.length; k++)
      	{
       %>
        <option value="<%=mysubjects[k]%>"><%=mysubjects[k]%></option>
        <%} %>
      </select>
    </div>
</div>
<div class="layui-form-item" id="div_checktestover" style="position:absolute;top: 0;left:350px;">
      <button type="button"class="layui-btn" id="bt_checktestover">查询</button>
 </div>
<div class="layui-form-item" id="div_subjectname" style="position:absolute;left:25px;width: 500px;height: 40px;display: none;">
    <h1 id="h1_subjectname">科目</h1>
</div>
<div class="layui-form-item" id="div_pagetype" style="position:absolute;left:525px;width: 200px;height: 40px;display: none;">
    <h1 id="h1_pagetype">成绩单</h1>
</div>
 <div class="layui-form-item" id="div_testcard" style="position:absolute;top: 0;left:800px;display: none;">
      <button type="button"class="layui-btn" id="bt_testcard">成绩单</button>
 </div>
<div class="layui-form-item" id="div_testresult" style="position:absolute;top: 0;left:980px;display: none;">
      <button type="button"class="layui-btn" id="bt_testresult">试卷评阅结果</button>
 </div>
 <div class="layui-form-item" id="div_exitlookscore" style="position:absolute;top: 0;left:1200px;display: none;">
      <button type="button"class="layui-btn" id="bt_exitlookscore">退出查询</button>
 </div>
 	</form>
<div id="div_testcardtb" style="
		  		position:absolute; 
		  	left: 120px; top:40px;display: none;">
	<table class="layui-hide" id="tb_testcard" lay-filter="tb_testcard"></table>
</div>
<div id="div_CEcharts" style="position:absolute;left:15px;width:640px;height:490px; top:150px;display:none;">
</div>
<div id="div_PEcharts" style="position:absolute;left:675px;width:640px;height:490px; top:150px;display:none;">
<div id="div_PEchartscenter" style="position:relative;left:170px;width:300px;height:30px; top:200px;display:none;">
</div>
</div>
<div id="div_testresulttb" style="
		  		position:absolute; 
		  	left: 10px; top:40px;display: none;">
<table class="layui-hide" id="tb_testresult" lay-filter="tb_testresult"></table>
</div>
</div>

<script type="text/html" id="bar_answerquestion">
  <a class="layui-btn layui-btn-xs" lay-event="answer">作答</a>
</script>
<script type="text/html" id="bar_testresult">
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
  	 		
window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/shome.jsp');
     
//学生首页       
table.render({
    elem: '#tb_studentinfo'
    ,cols: [[ //表头
      {field: 'sid', title: '学号', width:80, width:150,unresize:true}
      ,{field: 'sname', title: '姓名', width:100,unresize:true}
      ,{field: 'ssex', title: '性别', width:70,unresize:true}
      ,{field: 'sclass', title: '班级', width:400,unresize:true} 
      ,{field: 'ssubjects', title: '考试科目', width: 400,unresize:true}
    ]]
    ,data:[{
    	"sid":"<%=studentinfo.getSid()%>",
    	"sname":"<%=studentinfo.getSname()%>",
    	"ssex":"<%=studentinfo.getSsex()%>",
    	"sclass":"<%=studentinfo.getSclass()%>",
    	"ssubjects":"<%=studentinfo.getSsubjects()%>"
    }]
    ,even:true	
  });
//学生答题
  $("#bt_begintest").click(function(){
  	  var opsubject = $("#testsubject option:selected").val();
 	 if(opsubject == "zero"){
  		layer.msg('未选择科目！');
  		}else{
  			    $.ajax({
					url:'CheckTestStateServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					//有中文字符，需要加上引号
    					'sclass': '<%=studentinfo.getSclass()%>',
    					'subject': opsubject
    				},
    				success:function(res){
    					if(res == "非考试时间！" || res == "题目暂未发布！"){
    						layer.msg(res);
    					}else{
    						$("#div_selectsubject").css("display", "none");
    						$("#div_begintest").css("display", "none");
    						$("#h2_subject").text("科目：" + opsubject);
    						$("#h2_totalpoints").text("总分：" + res[2]);
    						$("#h2_testtime").text("考试时间：" + res[0].split(".")[0] + "至" + res[1].split(".")[0]);
    						$("#div_testhead").css("display", "block");
    						layer.msg('请开始答题！');
    						//载入题目表格
table.render({
    elem: '#tb_test'
    ,url: 'ShowQToStudentServlet' //数据接口
    ,even:true
    ,method: 'get'
    ,height:590
    ,limits:[6,12]
    ,limit:12
    ,where: {sclass: '<%=studentinfo.getSclass()%>', tsubject: opsubject}
    ,page:true
    ,cols: [[
      {field: 'id', title: '题号', width:80,sort: true,unresize:true}
      ,{field: 'point', title: '分数', width: 70,unresize:true}
      ,{field: 'topic', title: '题干', width:410,unresize:true}
      ,{field: 'knowledge', title: '考察点', width:240,unresize:true} 
      ,{field: 'myanswer', title: '我的作答', width: 410,unresize:true}
      ,{field: 'right', title: '操作', width: 70,toolbar:'#bar_answerquestion'}
    ]]
  });
    					}
    				},
    				error:function(res){
    				}
    			});
  		}
  });
  //监听行工具事件  学生答题
  table.on('tool(tb_test)', function(obj){
  	    var data = obj.data;
	if(obj.event === 'answer'){
    	layer.open({
    		type: 2
    		,title: '题目作答'
    		,content: 'AnswerQuestion.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['保存提交']
    		,yes:function(index, layero){
    			//回调的数值
    			var id = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_id").innerText;
    			var point = $(layero).find('iframe')[0].contentWindow.document.getElementById("h3_point").innerText;
    			var topic = $(layero).find('iframe')[0].contentWindow.document.getElementById("topic").value;
    			var knowledge = $(layero).find('iframe')[0].contentWindow.document.getElementById("knowledge").value;
    			var myanswer = $(layero).find('iframe')[0].contentWindow.document.getElementById("myanswer").value;
    			$.ajax({
					url:'StoreAnswerServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'sid': <%=studentinfo.getSid()%>,
    					'sclass': '<%=studentinfo.getSclass()%>',
    					'subject': $("#testsubject option:selected").val(),
    					'id': id,
    					'myanswer': myanswer
    				},
    				success:function(res){
    				//同步更新表格数据(非表格重载)
    			obj.update({
    				id: id,
    				point: point,
    				topic: topic,
    				knowledge: knowledge,
    				myanswer: myanswer
    			});
    					layer.msg(res);
    					layer.close(index);
    				},
    				error:function(res){
    					layer.msg('提交保存失败，请重试！');
    				}
    			});
    		}
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_answerq');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#h3_id').text(data.id);
    			body.find('#h3_point').text(data.point);
    			body.find('#topic').val(data.topic);
    			body.find('#knowledge').val(data.knowledge);
    			body.find('#myanswer').val(data.myanswer);
    		}
    	});
    }
  });
//查询按钮
$("#bt_checktestover").click(function(){
	var opsubject = $("#testsubject2 option:selected").val();
 	if(opsubject== "zero"){
  		layer.msg('未选择科目！');
  	}else{
  		//查询考试是否批改完毕()
    			$.ajax({
					url:'ShowQToStudentServlet',
    				type: 'post',
    				dataType:'json',
    				data:{
    					'sclass': '<%=studentinfo.getSclass()%>',
    					'subject': opsubject
    				},
    				success:function(res){
    					if(res == "该科目还未发布考试或机器还未批改完毕，请稍后再查询！"){
    						layer.alert(res, {icon: 2});
    					}else if(res == "canlook"){
    						layer.msg('请点击查看成绩单或试卷评阅！');
    						$("#div_selectsubject2").css("display", "none");
    						$("#div_checktestover").css("display", "none");
    						$("#h1_subjectname").text('科目：' + opsubject);
    						$("#div_subjectname").css("display", "block");
    						$("#div_testcard").css("display", "block");
    						$("#div_testresult").css("display", "block");
    						$("#div_testresult").css("display", "block");
    						$("#div_exitlookscore").css("display", "block");
    					}
    				},
    				error:function(res){
    				}
    			});
  	}
});
//退出查询按钮
$("#bt_exitlookscore").click(function(){
	window.location.reload(true);
});
//试卷评阅结果按钮
$("#bt_testresult").click(function(){
	var opsubject = $("#testsubject2 option:selected").val();
	$("#h1_pagetype").text('试卷评阅结果');
    $("#div_pagetype").css("display", "block");
    $("#div_testcardtb").css("display", "none");
    $("#div_testresulttb").css("display", "block");
    $("#div_PEcharts").css("display", "none");
    $("#div_CEcharts").css("display", "none");
 table.render({
    elem: '#tb_testresult'
    ,url: 'StudentScoreServlet' 
    ,method: 'post'
    ,height:570
    ,where: {sid: <%=studentinfo.getSid()%>, classname: '<%=studentinfo.getSclass()%>', subject: opsubject}
    ,even:true
    ,page:true
    ,limits:[6,12,20,50]
    ,limit:12
    ,cols: [[
      {field: 'id', title: '题号', width:90,sort: true,unresize:true}
      ,{field: 'topic', title: '题干', width:200,unresize:true}
      ,{field: 'knowledge', title: '考察点', width:170,unresize:true}
      ,{field: 'answer', title: '参考答案', width:250,unresize:true}
      ,{field: 'myanswer', title: '我的作答', width:250,unresize:true}
      ,{field: 'ppoint', title: '教师评分', width:90,unresize:true}
      ,{field: 'cpoint', title: '机器评分', width:90,unresize:true}
      ,{field: 'point', title: '本题分数', width: 90,unresize:true}
      ,{field: 'right', title: '操作', width: 70,toolbar:'#bar_testresult'}
    ]]
  });
});
//监听行工具事件  试卷评阅结果详情
  table.on('tool(tb_testresult)', function(obj){
    var data = obj.data;
	if(obj.event === 'look'){
    	layer.open({
    		type: 2
    		,title: '题目评阅详情'
    		,content: 'MyTestResult.html'
    		,maxmin: true
    		,area: ['820px','720px']
    		,btn: ['关闭']
    		,success:function(layero,index){
    			var div = layero.find('iframe').contents().find('#div_mytestresult');
    			var body = layer.getChildFrame('body',index);
    			var iframeWindow = window['layui-layer-iframe'+index];
    			body.find('#h3_id').text(data.id);
    			body.find('#h3_ppoint').text(data.ppoint);
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
//成绩单按钮
$("#bt_testcard").click(function(){
	var opsubject = $("#testsubject2 option:selected").val();
	$("#h1_pagetype").text('成绩单(机评)');
	$("#div_pagetype").css("display", "block");
	$("#div_testresulttb").css("display", "none");
	$("#div_testcardtb").css("display", "block");
	 table.render({
    elem: '#tb_testcard'
    ,url: 'StudentScoreServlet' 
    ,method: 'get'
    ,where: {sid: <%=studentinfo.getSid()%>, classname: '<%=studentinfo.getSclass()%>', subject: opsubject}
    ,even:true
    ,cols: [[
   		{field: 'cranking', title: '总排名', width:130,unresize:true}
   		,{field: 'pranking', title: '班排名', width:130,unresize:true}
      ,{field: 'sclass', title: '班级', width:200, unresize:true}
      ,{field: 'sid', title: '学号', width:200,unresize:true}
      ,{field: 'sname', title: '姓名', width: 150,unresize:true}
      ,{field: 'ssex', title: '性别', width:100,unresize:true}
      ,{field: 'ctotalpoint', title: '总分', width: 150,unresize:true}
    ]]
  });
function Data(name, max){
	this.name = name;
	this.max = max;
};
$.ajax({
		url:'MyScoreAnalysisServlet',
    	type: 'post',
    	dataType:'json',
    	data:{
    		'sid': <%=studentinfo.getSid()%>,
    		'sclass': '<%=studentinfo.getSclass()%>',
    		'subject': opsubject
    		},
    		success:function(res){
				$("#div_CEcharts").css("display", "block");
				var a = new Array(); //知识点&总分
				var b = new Array(); //得分
				for(var i=0; i<res.length; i++){
					a[i] = new Data(res[i].knowledge, res[i].point);
					b[i] = res[i].cpoint;
				}
				var myChart = echarts.init(document.getElementById('div_CEcharts'));
				var	option = {
					    legend: {
					        data: ['知识点掌握水平（机器评阅）']
					    },
					    radar: {
					        indicator: a
					    },
					    series: [{
					        name: '知识点掌握水平（机器评阅）',
					        type: 'radar',
					        data: [
					            {
					                value: b,
					                name: '知识点掌握水平（机器评阅）'
					            },
					        ]
					    }]
					};
				myChart.setOption(option);
    		},
    		error:function(res){
    		}
    	});
$.ajax({
		url:'MyScoreAnalysisServlet',
    	type: 'get',
    	dataType:'json',
    	data:{
    		'sid': <%=studentinfo.getSid()%>,
    		'sclass': '<%=studentinfo.getSclass()%>',
    		'subject': opsubject
    		},
    		success:function(res){
    			$("#div_PEcharts").css("display", "block");
    			if(res != "error"){
	    			var a = new Array(); //知识点&总分
					var b = new Array(); //得分
					for(var i=0; i<res.length; i++){
						a[i] = new Data(res[i].knowledge, res[i].point);
						b[i] = res[i].ppoint;
					}
					var myChart = echarts.init(document.getElementById('div_PEcharts'));
					var	option = {
						    legend: {
						        data: ['知识点掌握水平（人工评阅）']
						    },
						    radar: {
						        indicator: a
						    },
						    series: [{
						        name: '知识点掌握水平（人工评阅）',
						        type: 'radar',
						        data: [
						            {
						                value: b,
						                name: '知识点掌握水平（人工评阅）'
						            },
						        ]
						    }]
						};
					myChart.setOption(option);
    			}else{
    				$("#div_PEchartscenter").css("display", "block");
    				$("#div_PEchartscenter").html("教师暂未评阅。");
    			}
    		},
    		error:function(res){
    		}
    	});
});
//评分对比
  <%//修改密码成功
          String result_suppwd = (String)request.getAttribute("result_suppwd");
          if(result_suppwd != null){
          %>
          window.history.replaceState({},'','http://127.0.0.1:8080/AutoTest/shome.jsp');
          document.getElementById("sidenavli2").click();
        document.getElementById("sudspwdonclick").click();
    		layer.alert('<%=result_suppwd%>', {icon: 1});
      <%}%>
   });
   
   function exit(){
  window.location="login.jsp"
  }
  function pwdshow(){
   	document.getElementById("inputnewpwd").type = "text";
   }
   function pwdhidden(){
   	document.getElementById("inputnewpwd").type = "password";
   }
   function uppwd(){
   	<%
   		session.setAttribute("sid",studentinfo.getSid());
   	%>
   }
   function home() {
  document.getElementById("homearea").style.display = "block";
  document.getElementById("udpwdarea").style.display = "none";
  document.getElementById("testarea").style.display = "none";
  document.getElementById("lookscorearea").style.display = "none";
}
   function udpwd() {
  document.getElementById("homearea").style.display = "none";
   document.getElementById("udpwdarea").style.display = "block";
   document.getElementById("testarea").style.display = "none";
   document.getElementById("lookscorearea").style.display = "none";
}
   function begintest() {
  document.getElementById("homearea").style.display = "none";
   document.getElementById("udpwdarea").style.display = "none";
   document.getElementById("testarea").style.display = "block";
   document.getElementById("lookscorearea").style.display = "none";
}
   function lookscore() {
  document.getElementById("homearea").style.display = "none";
   document.getElementById("udpwdarea").style.display = "none";
   document.getElementById("testarea").style.display = "none";
   document.getElementById("lookscorearea").style.display = "block";
}
    </script>
  </body>
</html>
