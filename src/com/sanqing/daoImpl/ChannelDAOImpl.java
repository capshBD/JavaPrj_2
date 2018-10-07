package com.sanqing.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sanqing.bean.Channel;
import com.sanqing.dao.ChannelDAO;
import com.sanqing.util.DBConnection;

public class ChannelDAOImpl implements ChannelDAO {

	@Override
	public List<Channel> findAllChannel() {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Channel> channels=new ArrayList<Channel>();
		String findAllSql="select * from tb_channel";
		
		try {
			pstmt=conn.prepareStatement(findAllSql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Channel ch=new Channel();
				ch.setChannelID(rs.getInt(1));
				ch.setChannelName(rs.getString(2));
				channels.add(ch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		
		return channels;
	}

	@Override
	public void addChannel(Channel channel) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		String addSql="insert into tb_channel values(null,?)";
		
		try {
			pstmt=conn.prepareStatement(addSql);
			pstmt.setString(1, channel.getChannelName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
	}

}
