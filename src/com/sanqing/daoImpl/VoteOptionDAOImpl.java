package com.sanqing.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sanqing.bean.VoteOption;
import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.util.DBConnection;

public class VoteOptionDAOImpl implements VoteOptionDAO {

	@Override
	public void addVoteOption(VoteOption voteOption) {
		Connection conn=DBConnection.getConnection();
		String addSql="insert into tb_voteoption values(null,?,?,?)";
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(addSql);
			pstmt.setInt(1, voteOption.getVoteID());
			pstmt.setString(2, voteOption.getVoteOptionName());
			pstmt.setInt(3, voteOption.getTicketNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
	}

	@Override
	public List<VoteOption> findVoteOptionByVoteID(int voteID) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<VoteOption> voteOptions=new ArrayList<VoteOption>();
		String findSql="select * from tb_voteoption where voteID=?";
		
		try {
			pstmt=conn.prepareStatement(findSql);
			pstmt.setInt(1, voteID);
			rs=pstmt.executeQuery();
			while(rs.next()){
				VoteOption voteOption=new VoteOption();
				voteOption.setVoteOptionID(rs.getInt(1));
				voteOption.setVoteID(rs.getInt(2));
				voteOption.setVoteOptionName(rs.getString(3));
				voteOption.setTicketNum(rs.getInt(4));
				voteOptions.add(voteOption);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
		return voteOptions;
	}

	@Override
	public void deleteVoteOptionByID(int voteID) {
		Connection conn=DBConnection.getConnection();
		String deleteSql="delete from tb_voteoption where voteID=?";
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
	public void updateTicketNum(int voteOptionID) {
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qreryCurrentNumSql="select ticketNum from tb_voteoption where voteOptionID=?";
		String updateSql="update tb_voteoption set ticketNum=? where voteOptionID=?";
		int Num=0;
		
		try {
			pstmt=conn.prepareStatement(qreryCurrentNumSql);
			pstmt.setInt(1, voteOptionID);
			rs=pstmt.executeQuery();
			if(rs.next()){
				Num=rs.getInt(1);
			}
			pstmt=conn.prepareStatement(updateSql);
			pstmt.setInt(1, Num+1);
			pstmt.setInt(2, voteOptionID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
	}

}
