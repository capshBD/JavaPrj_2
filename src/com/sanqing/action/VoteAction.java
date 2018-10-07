package com.sanqing.action;

import java.util.ArrayList;
import java.util.List;

import com.sanqing.bean.Vote;
import com.sanqing.bean.VoteOption;
import com.sanqing.bean.VoteResult;
import com.sanqing.dao.VoteDAO;
import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.factory.VoteDAOFactory;
import com.sanqing.factory.VoteOptionDAOFactory;
import com.sanqing.util.Page;
import com.sanqing.util.PageUtil;

@SuppressWarnings("serial")
public class VoteAction extends BaseAction{
	private int currentPage;
	private int channelID;
	private String voteName;
	private String[]voteOption;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	public String getVoteName() {
		return voteName;
	}
	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}
	public String[] getVoteOption() {
		return voteOption;
	}
	public void setVoteOption(String[] voteOption) {
		this.voteOption = voteOption;
	}
	
	public String add()throws Exception{
		VoteDAO voteDAO=VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO=VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		Vote vote=new Vote();
		vote.setChannelID(channelID);
		vote.setVoteName(voteName);
		voteDAO.addVote(vote);
		
		int voteID=voteDAO.findVoteByName(voteName).getVoteID();
		
		for(String voteOptionName:voteOption){
			VoteOption vp=new VoteOption();
			vp.setVoteID(voteID);
			vp.setVoteOptionName(voteOptionName);
			voteOptionDAO.addVoteOption(vp);
		}
		return SUCCESS;
	}
	
	public String show()throws Exception{
		VoteDAO voteDAO=VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO=VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		int totalCount=voteDAO.findAllCount();
		Page page=PageUtil.createPage(5, totalCount, currentPage);
		List<Vote> votes=voteDAO.findAllVote(page);
		List<VoteResult> voteResults=new ArrayList<VoteResult>();
		
		for(Vote vote:votes){
			VoteResult voteResult=new VoteResult();
			List<VoteOption> voteOptions=voteOptionDAO.findVoteOptionByVoteID(vote.getVoteID());
			voteResult.setVote(vote);
			voteResult.setVoteOptions(voteOptions);
			voteResults.add(voteResult);
		}
		
		request.setAttribute("voteResults", voteResults);
		request.setAttribute("page", page);
		return "showvote";
	}
	
	public String delete() throws Exception{
		String voteId=getParameter("voteID");
		int  voteID=Integer.parseInt(voteId);
		
		VoteDAO voteDAO=VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO=VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		voteOptionDAO.deleteVoteOptionByID(voteID);
		voteDAO.deleteVote(voteID);
		setSimpleJsonResult(true,"删除成功");
		
		return JSON;
	} 
}
