package cn.solr.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import cn.solr.bean.SuggestToSolr;
import cn.solr.bean.SuggestValue;

import com.opensymphony.xwork2.ActionSupport;

public class SuggestAction extends ActionSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String keyword;
	public List<SuggestValue> best_suggest;


     public List<SuggestValue> getBest_suggest() {
		return best_suggest;
	}
	public void setBest_suggest(List<SuggestValue> best_suggest) {
		this.best_suggest = best_suggest;
	}
	@JSON(serialize=false)  
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		System.out.println(request.getCharacterEncoding());
		
		System.out.println(keyword);
		
		System.out.println("dddddddddddd");

//		List<SuggestValue> listtest = new ArrayList<SuggestValue>();
//		listtest.add(new SuggestValue("this is a test"));
//		listtest.add(new SuggestValue("this is a good"));
//		listtest.add(new SuggestValue("this is a fast"));
//		System.out.println(listtest.size());
//		this.best_suggest = listtest;
		
		SuggestToSolr test = new SuggestToSolr();
		this.best_suggest  =  test.doSuggest(keyword);
//		System.out.println(listtest.get(0).getValue());
//		JSONArray jsonArray2 = null;
//		try{
//		    jsonArray2 = JSONArray.fromObject(listtest);
//		    System.out.println("dddddddddddd");
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		System.out.println("dddddddddddd");
//		
//		System.out.println(jsonArray2== null);
//		System.out.println("dddddddddddd");
//		
//		this.best_pictures = "[{\"value\":\"this is a test\"},{\"value\": \"this is a good\"}]";
//		System.out.println(this.best_pictures);
//		return this.best_pictures.toString();
		return "success";
	}
//	private boolean isvalideUser(String userLogin, String password)
//	{
//		if(user.getUserLoginName().trim().equals(userLogin) && user.getUserPassword().trim().equals(password))
//		{
//			Map session=ActionContext.getContext().getSession();
//			session.put("user.userLoginName", user.getUserLoginName());
//			System.out.println("登录成功,用户名="+user.getUserLoginName());
//			return true;
//		}
//		else
//		{
//			return false;
//		}
//	}

	
}
