package com.yunqi.config.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

public class SimpleSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
	private SimpleFilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}
	
	public void setSecurityMetadataSource(SimpleFilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

//	private SimpleFilterInvocationSecurityMetadataSource securityMetadataSource;
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		FilterInvocation fi = new FilterInvocation(request, response, chain);
//		invoke(fi);
//	}
//
//	public Class<? extends Object> getSecureObjectClass() {
//		return FilterInvocation.class;
//	}
//
//	public void invoke(FilterInvocation fi) throws IOException, ServletException {
//		// object为FilterInvocation对象
//		// super.beforeInvocation(fi);源码
//		// 1.获取请求资源的权限
//		// 执行Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object);
//		// 2.是否拥有权限
//		// this.accessDecisionManager.decide(authenticated, object, attributes);
//		InterceptorStatusToken token = super.beforeInvocation(fi);
//		try {
//			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//		} finally {
//			super.afterInvocation(token, null);
//		}
//
//	}
//
//	@Override
//	public SecurityMetadataSource obtainSecurityMetadataSource() {
//		return securityMetadataSource;
//	}
//
//	public void destroy() {
//
//	}
//
//	public void init(FilterConfig filterconfig) throws ServletException {
//
//	}
//


}