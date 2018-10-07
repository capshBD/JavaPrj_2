<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投票系统前台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="user/css/index.css">
	
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		$(function(){
			$.post('channel_findAll.action',function(data){
				  if(data.length<1){
				  	alert('暂时没有任何频道,请等待或联系管理员');
				  	return;
				  }
				  var chID=document.getElementById('chList');
				  var li;
				  $.each(data,function(index,chan){
				  	li=document.createElement('li');
				  	li.innerHTML="<a target='cnt' href='elec_showChannel.action?channelID="+chan.channelID+"'>"+chan.channelName+"</a>";
		          	chID.appendChild(li);
				  })
				},'json')
			});
	</script>
  </head>
  
  <body>
  	<div>
  		<div id="clit">
  		<ul id="chList"></ul>
  		</div>
  		<div id="lg">发起投票</div>
  		<div id="voteContent">
  			<iframe name="cnt" src="elec_showChannel.action?channelID=1" scrolling="no" frameborder="0" onload="this.height=cnt.document.body.scrollHeight"></iframe>
  		</div>
  	</div>
  </body>
</html>
