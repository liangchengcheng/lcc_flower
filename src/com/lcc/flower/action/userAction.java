package com.lcc.flower.action;

import java.util.Map;

import com.lcc.flower.model.User;
import com.lcc.flower.model.Userdetail;
import com.lcc.flower.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class userAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Userdetail userdetail;
	private IUserService userService;
	private String password;
	private String pwd;
	private int userid;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Userdetail getUserdetail() {
		return userdetail;
	}
	public void setUserdetail(Userdetail userdetail) {
		this.userdetail = userdetail;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	/**
	 * 添加用户(类似用户注册)
	 * @return
	 * @throws Exception
	 */
	public String addUser() throws Exception{
		Map session = (Map)ActionContext.getContext().getSession();
		User user1 = new User();
		Userdetail userdetail1 = new Userdetail();
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		user1.setRole(user.getRole());
		
		userdetail1.setAddress(userdetail.getAddress());
		userdetail1.setCsrq(userdetail.getCsrq());
		userdetail1.setXb(userdetail.getXb());
		userdetail1.setEmail(userdetail.getEmail());
		userdetail1.setPhone(userdetail.getPhone());
		userdetail1.setTruename(userdetail.getTruename());
		userdetail1.setUser(user1);
		
		user1.setUserdetail(userdetail1);
		if (userService.addOrUpdateUser(user1)) {
			return SUCCESS;
		}
		
		return ERROR;
	}
	
	/**
	 * 检测用户是否存在
	 * @return
	 * @throws Exception
	 */
	public String checkUser()throws Exception{
		Map session =(Map) ActionContext.getContext().getSession();
		User user1 = userService.checkUser(user);
		if (user1!=null) {
			session.put("user", user1);
			return SUCCESS;
		}
		session.put("role", user1.getRole());
		return ERROR;
	}
	
	/**
	 * 退出登录
	 * @return
	 * @throws Exception
	 */
	public String logOut() throws Exception{
		Map session =ActionContext.getContext().getSession();
		session.remove("user");
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	public String updateUserPassword() throws Exception {
		Map session =(Map)ActionContext.getContext().getSession();
		User user1 = (User) session.get("user");
		if (user1.getPassword().equals(pwd)) {
			Userdetail userdetail1=user1.getUserdetail();
			user1.setPassword(password);
			userService.addOrUpdateUser(user1);
			session.remove("user");
			return SUCCESS;
		}
		Map request =(Map)ActionContext.getContext().get("request");
		request.put("msg", "密码不正确");
		return ERROR;
	}
	
	/**
	 * 编辑用户的信息
	 * @return
	 * @throws Exception
	 */
	public String updateUserDatail() throws Exception {
		Map session=(Map) ActionContext.getContext().getSession();
		User user1 = (User) session.get("user");
		user1.getUserdetail().setAddress(userdetail.getAddress());
		user1.getUserdetail().setXb(userdetail.getXb());
		user1.getUserdetail().setCsrq(userdetail.getCsrq());
		user1.getUserdetail().setEmail(userdetail.getEmail());
		user1.getUserdetail().setPhone(userdetail.getPhone());
		user1.getUserdetail().setTruename(userdetail.getTruename());
		
		if (userService.addOrUpdateUser(user1)) {
			session.put("user", user1);
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String guashiUser() throws Exception {
		if (userService.guashiUser(userid)) {
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String jieguaUser()throws Exception{
		if (userService.jieguaUser(userid)) {
			return SUCCESS;
		}
		return ERROR;
	}
	
}
