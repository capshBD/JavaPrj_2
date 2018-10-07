<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>频道投票</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="user/css/showChVote.css">
	<script type="text/javascript">
		function addOption(){
			var otherOp=document.getElementById('otherOp');
			var orp=document.getElementById('otherOption');
			if(orp){
				return;
			}
			
			var inputOP=document.createElement('input');
				inputOP.type='text';
				inputOP.id='otherOption';
				inputOP.name='otherOption';
			otherOp.appendChild(inputOP);	
		}
		
		function removeOption(){
			var  otherOp=document.getElementById('otherOp');
			var  orp=document.getElementById('otherOption');
			if(!orp){
				return;
			}
			otherOp.removeChild(orp);
		}
	</script>

  </head>
  
  <body>
  		<div id="main">
  			<p style="color:red">
  				${error}
  			</p>
  			<s:iterator value="#request.voteResults" var="voteRs">
  				<p>${voteRs.vote.voteName}</p>
  				<form action="elec_doVote.action" method="post">
  					<ul>
  						<s:iterator value="#voteRs.voteOptions" var="voteOptn">
  							<li>
  								<input type="radio" name="voteOptionID" value="${voteOptn.voteOptionID}" onclick="removeOption()"/>${voteOptn.voteOptionName}
  							</li>
  						</s:iterator>
  							<li><input type="radio"  name="voteOptionID" value="0" onclick="addOption()"/>其他</li>
  							<input type="hidden"  name="currentPage" value="${page.currentPage}"/>
  							<li id="otherOp"></li>
  								<input type="hidden" name="voteID"    value="${voteRs.vote.voteID}"/>
  								<input type="hidden" name="channelID" value="${voteRs.vote.channelID}"/>
  							<li><a href="elec_voteResult.action?voteID=${voteRs.vote.voteID}">查看投票结果</a></li>
  							<li><input type="submit" value="投票"/><input type="reset" value="重投"/></li>
  					</ul>
  				</form>
  			</s:iterator>
  			<s:if test="#request.voteResults.size==0">该频道下还没有投票内容,请等待管理员添加</s:if>
  		</div>
  			<s:else>
  		<div id="pageInfo">
  			<c:choose>
    		<c:when test="${page.hasPrePage}">
    			<a href="elec_showChannel.action?currentPage=1&channelID=${chID}">首页</a>|
    			<a href="elec_showChannel.action?currentPage=${page.currentPage-1}&channelID=${chID}">上一页</a>|
    		</c:when>
    		<c:otherwise>
    		             首页|上一页|
    		</c:otherwise>
    	</c:choose>
    	<c:choose>
    		<c:when test="${page.hasNextPage}">
    			<a href="elec_showChannel.action?currentPage=${page.currentPage+1}&channelID=${chID}">下一页</a>|
    			<a href="elec_showChannel.action?currentPage=${page.totalPage}&channelID=${chID}">末页</a>|
    		</c:when>
    		<c:otherwise>
    			下一页|末页|
    		</c:otherwise>
    	</c:choose>
    	第${page.currentPage}页|共${page.totalPage}页
  		</div>
  			</s:else>
  </body>
</html>
