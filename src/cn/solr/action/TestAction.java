package cn.solr.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String username;
	public String password;
	public String query;
	
//	private User user;
//	private UserDAO userDAO;
	
//	public User getUser() {
//		System.out.println("GETUser++++++++");
//		return user;
//	}
//
//	public void setUser(User user) {
//		System.out.println("SetUser++++++++");
//		this.user = user;
//	}
//	
//	public void setUserDAO(UserDAO userDAO)
//	{
//		this.userDAO = userDAO;
//	}

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		System.out.println(request.getCharacterEncoding());
		
		System.out.println(username);
		
		System.out.println(password);
		System.out.println(query);
//		List<User> userlist = (List<User>)userDAO.findAll();
//		System.out.println("用户表的行数 ：  " + userlist.size());
//		for(int i = 0; i < userlist.size(); i++)
//		{
//			User usertemp = userlist.get(i);
//			if(isvalideUser(usertemp.getUserLoginName(),usertemp.getUserPassword()))
//			{
//				this.user.setUserId(usertemp.getUserId());
//				this.user.setUserName(usertemp.getUserName());
//				return "success";
//			}
//		}
//		System.out.println("登录失败，用户名="+user.getUserLoginName());
		
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
