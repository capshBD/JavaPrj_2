<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>投票系统后台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">
	

  </head>
  
  <body>
  	<div id="main">
  		<div id="menu">
  			<div class="tab">
  			 <ul>
  			 	<li><a href="addVote.jsp" target="votemanager"><img src="../img/add.PNG"/></a></li>
  			 	<li><a href="addVote.jsp" target="votemanager">新增投票</a></li>
  			 </ul>
  			</div>
  			<div class="tab">
  			 <ul>
  			 	<li><a href="<%=path%>/vote_show.action" target="votemanager"><img src="../img/votemanger.gif"/></a></li>
  			 	<li><a href="<%=path%>/vote_show.action" target="votemanager">投票管理</a></li>
  			 </ul>
  			</div>
  		</div>
  		<div id="center">
  			<iframe name="votemanager" frameborder="0" scrolling="no" onload="this.height=votemanager.document.body.scrollHeight" src="addVote.jsp"/>
  		</div>
  	</div>
  </body>
</html>
