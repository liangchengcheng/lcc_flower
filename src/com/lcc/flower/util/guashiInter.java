package com.lcc.flower.util;

import java.util.List;
import java.util.Map;

import com.lcc.flower.action.userAction;
import com.lcc.flower.model.Guashi;
import com.lcc.flower.service.IUserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class guashiInter extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		userAction userAction = (com.lcc.flower.action.userAction) arg0.getAction();
		String username = userAction.getUser().getUsername();

		List list = userService.getGuashi();
		for (int i = 0; i < list.size(); i++) {
			if (((Guashi) list.get(i)).getUser().getUsername().equals(username)) {
				Map request = (Map) arg0.getInvocationContext().get("request");
				request.put("msg", "错误信息");
				return Action.ERROR;
			}
		}
		return arg0.invoke();
	}

}
