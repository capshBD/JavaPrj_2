<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>投票列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="admin/css/showVote.css">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		function deleteVote(voteID){
			 var del=document.getElementById('del');
			 var flg=window.confirm("该投票列表下的投票选项也将一并删除,是否继续?");
			 if(!flg){
			 	return;
			 }
			$.post('vote_delete.action',{'voteID':voteID},function(data){
				alert(data.info);
				pageReload();
			},'json');
		}
		
		function pageReload(){
			alert(location.href);
			location.reload();
		}
	</script>

  </head>
  
  <body>
  		<s:if test="#request.voteResults.size==0">还没有投票内容,请等待管理员添加</s:if>
  		<s:else>
  			<div>
  			<table bordercolor="blue" border="1" cellpadding="0" cellspacing="0">
  			<tr>
  				<td>投票序号</td>
  				<td>投票名</td>
  				<td>投票选项1</td>
  				<td>投票选项2</td>
  				<td>投票选项3</td>
  				<td>操作</td>
  			</tr>
  			<s:iterator value="#request.voteResults" var="voteResult">
			   <tr>
				<td><div>${voteResult.vote.voteID}</div></td>	
				<td><div>${voteResult.vote.voteName}</div></td>	
				<s:subset source="#voteResult.voteOptions" id="subVoteOp" count="3"/>	
				<s:iterator value="#attr.subVoteOp" var="voteOp">
				<td><div>${voteOp.voteOptionName}</div></td>
				</s:iterator>
				<td><div>
					<span><img src="img/close.gif" width="9" height="9"/></span>
					[<span><input id="del" type="button" onclick="deleteVote(${voteResult.vote.voteID})" value="删除"/></span>]
					</div>
				</td>
			   </tr>
  			</s:iterator>
  		</table>
  		</div>
  		<div id="pageInfo">
  			<c:choose>
    		<c:when test="${page.hasPrePage}">
    			<a href="vote_show.action?currentPage=1">首页</a>|
    			<a href="vote_show.action?currentPage=${page.currentPage-1}">上一页</a>|
    		</c:when>
    		<c:otherwise>
    		             首页|上一页|
    		</c:otherwise>
    	</c:choose>
    	<c:choose>
    		<c:when test="${page.hasNextPage}">
    			<a href="vote_show.action?currentPage=${page.currentPage+1}">下一页</a>|
    			<a href="vote_show.action?currentPage=${page.totalPage}">末页</a>|
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
