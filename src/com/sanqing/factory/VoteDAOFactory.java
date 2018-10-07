package com.sanqing.factory;

import com.sanqing.dao.VoteDAO;
import com.sanqing.daoImpl.VoteDAOImpl;

public class VoteDAOFactory {
	public static VoteDAO getVoteDAOInstance(){
		return new VoteDAOImpl();
	}
}
