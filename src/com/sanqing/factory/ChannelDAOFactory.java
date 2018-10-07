package com.sanqing.factory;

import com.sanqing.dao.ChannelDAO;
import com.sanqing.daoImpl.ChannelDAOImpl;

public class ChannelDAOFactory {
	public static ChannelDAO getChannelDAOInstance(){
		return new ChannelDAOImpl();
	}
}
