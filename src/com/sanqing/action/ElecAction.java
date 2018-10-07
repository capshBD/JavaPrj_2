package com.sanqing.action;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.sanqing.bean.Vote;
import com.sanqing.bean.VoteOption;
import com.sanqing.bean.VoteResult;
import com.sanqing.dao.VoteDAO;
import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.factory.VoteDAOFactory;
import com.sanqing.factory.VoteOptionDAOFactory;
import com.sanqing.util.Page;
import com.sanqing.util.PageUtil;

@SuppressWarnings("serial")
public class ElecAction extends BaseAction {
	
	private JFreeChart chart;

	public JFreeChart getChart() {
		VoteDAO voteDAO=VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO=VoteOptionDAOFactory.getVoteOptionDAOInstance();
		int voteID=Integer.parseInt(getParameter("voteID"));
		
		Vote vote=voteDAO.findVoteById(voteID);
		String voteName=vote.getVoteName();
		List<VoteOption> voteOptions=voteOptionDAO.findVoteOptionByVoteID(voteID);
		
		DefaultCategoryDataset dcd=new DefaultCategoryDataset();
		
		for(VoteOption voteOption:voteOptions){
			dcd.setValue(voteOption.getTicketNum(), "", voteOption.getVoteOptionName());
		}
		
		JFreeChart chart=ChartFactory.createBarChart3D(
				voteName,
				"投票选项", 
				"投票数", 
				dcd,
				PlotOrientation.VERTICAL,
				false,
				true,
				false
				);
		TextTitle textTitle=chart.getTitle();
		textTitle.setFont(new Font("黑体",Font.BOLD,20));
		
		CategoryPlot plot=(CategoryPlot) chart.getPlot();
		
		CategoryAxis axis=plot.getDomainAxis();
		axis.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		axis.setLabelFont(new Font("宋体",Font.BOLD,20));
		
		ValueAxis vaxis=plot.getRangeAxis();
		vaxis.setTickLabelFont(new Font("宋体",Font.BOLD,20));
		vaxis.setLabelFont(new Font("宋体",Font.BOLD,20));
		
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public String showChannel()throws Exception{
		String currentPg=getParameter("currentPage");
		String chID=getParameter("channelID");
		if(chID==null){
			chID="1";
		}
		int currentPage=0;
		int channelID=Integer.parseInt(chID);
		
		if(currentPg==null||currentPg.isEmpty()){
			if(request.getAttribute("currentPage")==null)
				currentPage=1;
			else
			currentPage=Integer.valueOf(request.getAttribute("currentPage").toString());
			
		}else{
			currentPage=Integer.parseInt(currentPg);
		}
		
		VoteDAO voteDAO=VoteDAOFactory.getVoteDAOInstance();
		VoteOptionDAO voteOptionDAO=VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		int totalCount=voteDAO.findAllCountByChId(channelID);
		Page page=PageUtil.createPage(1, totalCount, currentPage);
		List<Vote> votes=voteDAO.findVoteByChId(page, channelID);
		List<VoteResult> voteResults=new ArrayList<VoteResult>();
		
		for (Vote vote:votes) {
			VoteResult voteRs=new VoteResult();
			voteRs.setVote(vote);
			List<VoteOption> voteOptions=voteOptionDAO.findVoteOptionByVoteID(vote.getVoteID());
			voteRs.setVoteOptions(voteOptions);
			voteResults.add(voteRs);
		}
		
		request.setAttribute("voteResults", voteResults);
		request.setAttribute("page", page);
		request.setAttribute("chID", chID);
		
		return "showchannel";
	}
	
	public String doVote() throws Exception{
		String vpID=getParameter("voteOptionID");
		String vID=getParameter("voteID");
		int voteOptionID=Integer.parseInt(vpID);
		int voteID=Integer.parseInt(vID);
		VoteOptionDAO voteOptionDAO=VoteOptionDAOFactory.getVoteOptionDAOInstance();
		
		Cookie cookies[]=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getValue().equals(vID)){
				String currentPage=getParameter("currentPage");
				request.setAttribute("error", "您已经在该项目投过票了!");
				request.setAttribute("currentPage", currentPage);
				return INPUT;
			}
		}
		
		if(voteOptionID==0){
			String otherOption=getParameter("otherOption");
			VoteOption vp=new VoteOption();
			vp.setVoteID(voteID);
			vp.setVoteOptionName(otherOption);
			vp.setTicketNum(1);
			voteOptionDAO.addVoteOption(vp);
		}
	
		voteOptionDAO.updateTicketNum(voteOptionID);
		Cookie cookie=new Cookie("hasVote"+voteID, vID);
		response.addCookie(cookie);
		return "voteresult";
	}
	
	public String voteResult()throws Exception{
		return "voteRs";
	}
}
