<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发布投票</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="admin/css/addVote.css">
	
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$.post('channel_findAll.action',function(data){
		  if(data.length<1){
		  	alert('暂时没有任何频道,请先添加');
		  	addChannel();
		  	return;
		  }
		  var chID=document.getElementById('chID');
		  var op;
		  $.each(data,function(index,chan){
		  	op=new Option(chan.channelName,chan.channelID);
          	chID.add(op); 
		  })
		},'json')
	   });
	   
		var i=4;
		function addVoteOption(){
			var voteP=document.getElementById('voteOptionList');
			
			var inputDiv=document.createElement('div');
				inputDiv.id=i;
				i++;
			voteP.appendChild(inputDiv);
			
			var inputText=document.createTextNode('选项n名称:');
			inputDiv.appendChild(inputText);
			
			var inputP=document.createElement('input');
			inputP.type='text';
			inputP.name='voteOption';
			inputDiv.appendChild(inputP);
			
			var removeP=document.createElement('input');
			removeP.type='button';
			removeP.value='移除';
			removeP.onclick=removeVoteOption;
			inputDiv.appendChild(removeP);
		}
		
		function removeVoteOption(){
		    var id=this.parentNode.id;
			var optionNode=document.getElementById(id);
			optionNode.parentNode.removeChild(optionNode);
		}
		
		function addChannel(){
			var container=document.getElementById('container');
			var mainNode=document.getElementById('main');
			mainNode.parentNode.removeChild(mainNode);
			
			var chForm=document.createElement('form');
			chForm.action='channel_add.action';
			chForm.method='post';
			container.appendChild(chForm);
			
			var inputText=document.createTextNode('频道名称:');
			chForm.appendChild(inputText);
			
			var inputP=document.createElement('input');
			inputP.type='text';
			inputP.name='channelName';
			chForm.appendChild(inputP);
			
			var subP=document.createElement('input');
			subP.type='submit';
			subP.value='添加';
			chForm.appendChild(subP);
		}
	
	</script>
  </head>
  
  <body id="container">
  <div id="main">
  		<form action="vote_add.action" method="post" name="addForm">
  		<table>
  			<tr>
  				<td>选择投票频道:</td>
  				<td><select name="channelID" id="chID">
  					<option selected="selected" value="0">--请选择--</option>
  				    </select>
  					<input type="button" value="新增频道?" onclick="addChannel()"/>
  				</td>
  			</tr>
  			<tr>
  				<td>输入投票名称:</td>
  				<td><input type="text" name="voteName"/></td>
  			</tr>
  		</table>
  		<br>
  		<div id="voteOptionList">
  		<div>选项1名称:<input type="text" name="voteOption"/></div>
  		<div>选项2名称:<input type="text" name="voteOption"/></div>
  		<div>选项3名称:<input type="text" name="voteOption"/></div>
  		</div>
  		<input type="button" value="新增投票选项" onclick="addVoteOption()"/>
  		<input type="submit" value="发布"/><input type="reset" value="重置"/>
  	</form>
  </div>	
  </body>
</html>
