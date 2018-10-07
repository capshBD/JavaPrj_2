package com.sanqing.factory;

import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.daoImpl.VoteOptionDAOImpl;


public class VoteOptionDAOFactory {
	public static VoteOptionDAO getVoteOptionDAOInstance(){
		return new VoteOptionDAOImpl();
	}
}
