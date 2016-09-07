package com.lcc.flower.util;

import java.util.Map;

import com.lcc.flower.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截器
 * @author lcc
 *
 */
public class logininter extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map session = (Map)arg0.getInvocationContext().getSession();
		User user = (User) session.get("user");
		if (user == null) {
			return Action.LOGIN;
		}
		return null;
	}

}
