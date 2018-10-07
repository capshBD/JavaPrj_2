package com.sanqing.action;

import java.util.List;

import com.sanqing.bean.Channel;
import com.sanqing.dao.ChannelDAO;
import com.sanqing.factory.ChannelDAOFactory;

@SuppressWarnings("serial")
public class ChannelAction extends BaseAction{
	
	public String findAll()throws Exception{
		ChannelDAO channelDAO=ChannelDAOFactory.getChannelDAOInstance();
		List<Channel> channels=channelDAO.findAllChannel();
		setJsonObjectResult(channels);
		return JSON;
	}
	
	public String add()throws Exception{
		
		ChannelDAO channelDAO=ChannelDAOFactory.getChannelDAOInstance();
		Channel channel=new Channel();
		channel.setChannelName(getParameter("channelName"));
		channelDAO.addChannel(channel);
		return SUCCESS;
	}
}
