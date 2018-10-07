package com.sanqing.dao;

import java.util.List;

import com.sanqing.bean.Vote;
import com.sanqing.util.Page;

public interface VoteDAO {
	public void addVote(Vote vote);
	
	public void deleteVote(int voteID);
	
	public Vote findVoteByName(String voteName);
	
	public List<Vote> findAllVote(Page page);
	
	public int findAllCount();
	
	public List<Vote> findVoteByChId(Page page,int channelID);
	
	public int findAllCountByChId(int channelID);
	
	public Vote findVoteById(int voteID);
}
