package com.sanqing.action;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.opensymphony.xwork2.ActionSupport;

public class PieChart3DAction extends ActionSupport {
	private JFreeChart chart;

	public JFreeChart getChart() {
		chart=ChartFactory.createPieChart3D("统计报表分析", getDataSet(),  true, false, false);
		chart.setTitle(new TextTitle("统计报表分析", new Font("黑体",Font.BOLD,20)));
		LegendTitle legend=chart.getLegend();
		legend.setItemFont(new Font("黑体",Font.BOLD,20));
		PiePlot3D plot=(PiePlot3D) chart.getPlot();
		plot.setLabelFont(new Font("隶书",Font.ITALIC,18));
		//背景透明度
		plot.setBackgroundAlpha(0.9f);
		//前景透明度
		plot.setForegroundAlpha(0.50f);
		
		String unitStyle="{0}={1}({2})";
		//图例显示样式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator
	    (unitStyle, NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
		//标签显示样式
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator
	    (unitStyle, NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	private DefaultPieDataset getDataSet(){
		DefaultPieDataset dataSet=new DefaultPieDataset();
		dataSet.setValue("白金卡", 21);
		dataSet.setValue("钻石卡", 19);
		dataSet.setValue("VIP卡", 36);
		dataSet.setValue("准私人银行", 17);
		dataSet.setValue("私人银行", 26);
		dataSet.setValue("贵宾卡", 82);
		dataSet.setValue("其他", 13);
		return dataSet; 
	}
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
}
