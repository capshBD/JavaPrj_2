package com.sanqing.dao;

import java.util.List;

import com.sanqing.bean.VoteOption;

public interface VoteOptionDAO {
	public void addVoteOption(VoteOption voteOption);
	public List<VoteOption> findVoteOptionByVoteID(int voteID);
	public void deleteVoteOptionByID(int voteID);
	public void updateTicketNum(int voteOptionID);
}
