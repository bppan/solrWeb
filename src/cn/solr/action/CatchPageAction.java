package cn.solr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CatchPageAction extends ActionSupport{
	@Override
	public String execute() throws Exception {

		System.out.println("CatchPageAction");
		String htmlName = "clueweb09-enwp00-22-11078.html";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.sendRedirect("G:/html/" + htmlName);
		return "success";
	}
}
