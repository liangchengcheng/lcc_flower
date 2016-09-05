package com.lcc.flower.util;

import java.util.Map;

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
	
		return null;
	}

}
