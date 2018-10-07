package com.sanqing.dao;

import java.util.List;

import com.sanqing.bean.Channel;

public interface ChannelDAO {
	public List<Channel> findAllChannel();
	public void addChannel(Channel channel);
}
