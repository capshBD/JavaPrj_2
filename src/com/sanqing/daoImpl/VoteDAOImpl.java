package com.sanqing.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sanqing.bean.Vote;
import com.sanqing.dao.VoteDAO;
import com.sanqing.util.DBConnection;
import com.sanqing.util.Page;

public class VoteDAOImpl implements VoteDAO {

	@Override
	public void addVote(Vote vote) {
		Connection conn=DBConnection.getConnection();
		String addSql="insert into tb_vote values(null,?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(addSql);
			pstmt.setString(1, vote.getVoteName());
			pstmt.setInt(2, vote.getChannelID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
	}

	@Override
	public Vote findVoteByName(String voteName) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Vote vote=null;
		String findByNameSql="select * from tb_vote where voteName=?";
		
		try {
			pstmt=conn.prepareStatement(findByNameSql);
			pstmt.setString(1, voteName);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vote=new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		
		return vote;
	}

	@Override
	public List<Vote> findAllVote(Page page) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllVoteSql="select * from tb_vote order by voteID desc limit ?,? ";
		List<Vote> votes=new ArrayList<Vote>();
		try {
			pstmt=conn.prepareStatement(findAllVoteSql);
			pstmt.setInt(1, page.getBeginIndex());
			pstmt.setInt(2, page.getEveryPage());
			rs=pstmt.executeQuery();
			while(rs.next()){
				Vote vote=new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
				votes.add(vote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return votes;
	}

	@Override
	public int findAllCount() {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllCountSql="select count(*) from tb_vote";
		int num=0;
		try {
			pstmt=conn.prepareStatement(findAllCountSql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return num;
	}

	@Override
	public void deleteVote(int voteID) {
		Connection conn=DBConnection.getConnection();
		String deleteSql="delete from tb_vote where voteID=?";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(deleteSql);
			pstmt.setInt(1, voteID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
	}

	@Override
	public List<Vote> findVoteByChId(Page page, int channelID) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllVoteSql="select * from tb_vote where channelID=? order by voteID desc limit ?,? ";
		List<Vote> votes=new ArrayList<Vote>();
		try {
			pstmt=conn.prepareStatement(findAllVoteSql);
			pstmt.setInt(1, channelID);
			pstmt.setInt(2, page.getBeginIndex());
			pstmt.setInt(3, page.getEveryPage());
			rs=pstmt.executeQuery();
			while(rs.next()){
				Vote vote=new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
				votes.add(vote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return votes;
	}

	@Override
	public int findAllCountByChId(int channelID) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String findAllCountSql="select count(*) from tb_vote where channelID=?";
		int num=0;
		try {
			pstmt=conn.prepareStatement(findAllCountSql);
			pstmt.setInt(1, channelID);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return num;
	}

	@Override
	public Vote findVoteById(int voteID) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Vote vote=null;
		String findByIdSql="select * from tb_vote where voteID=?";
		
		try {
			pstmt=conn.prepareStatement(findByIdSql);
			pstmt.setInt(1, voteID);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vote=new Vote();
				vote.setVoteID(rs.getInt(1));
				vote.setVoteName(rs.getString(2));
				vote.setChannelID(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return vote;
	}

}
