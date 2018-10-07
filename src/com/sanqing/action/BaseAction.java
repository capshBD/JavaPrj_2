package com.sanqing.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.sanqing.util.SimpleJsonResult;
import com.sanqing.util.StringUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {
		public static final String JSON="json";
		
		protected ActionContext context=ActionContext.getContext();
		protected HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		protected HttpServletResponse response=(HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		protected HttpSession session=request.getSession();
		protected Object jsonResult;
		
		/**
		 * 获取请求参数
		 * @param name 参数名称
		 * @return
		 */
		protected String getParameter(String name) {
			String s = request.getParameter(name);
			return StringUtil.isNullString(s) ? null : s.trim();
		}

		/**
		 * 获取所有请求参数(忽略重复)
		 * @return
		 */
		protected Map<String, Object> getParameters() {
			Map<String, Object> parameters=new HashMap<String, Object>();
			Map<String, Object> map = context.getParameters();
			for (Entry<String, Object> entry : map.entrySet()) {
				String s = ((String[])entry.getValue())[0];
				if(!StringUtil.isNullString(s)){
					parameters.put(entry.getKey(), s.trim());
				}else{
					parameters.put(entry.getKey(), "");
				}
			}
			return parameters;
		}
		
		protected void write(String str) {
			try {
				response.getWriter().write(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		protected void setSimpleJsonResult(boolean success,String info){
			jsonResult=new SimpleJsonResult(success, info);
		}
		
		protected String successSimpleJsonResult(String info) {
			jsonResult=new SimpleJsonResult(true, info);
			return JSON;
		}
		
		protected void setJsonObjectResult(Object result) {
			jsonResult=result;
		}
		
		public Object getJsonResult() {
			return jsonResult;
		}
		
		public HttpServletRequest getServletRequest() {
				return this.request;
		}

}
