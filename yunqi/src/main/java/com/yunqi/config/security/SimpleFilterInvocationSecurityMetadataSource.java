package com.yunqi.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.yunqi.model.Resource;
import com.yunqi.model.Role;
import com.yunqi.service.ResourceService;
import com.yunqi.service.RoleService;

@Component
public class SimpleFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleService roleService;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	// 加载所有资源与权限的关系
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resource> resources = resourceService.findAll();
			// 加载资源对应的权限
			for (Resource resource : resources) {
				List<Role> roles = roleService.findRoleBySource(resource.getId());
				Collection<ConfigAttribute> auths = new ArrayList<>();
				for(Role r : roles){
					ConfigAttribute auth = new SecurityConfig(r.getName());
					auths.add(auth);
				}
				logger.debug("权限[{}]", auths);
				resourceMap.put(resource.getPermission(), auths);
			}
		}
	}

	// 加载所有资源与权限的关系
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object是一个URL，被用户请求的url
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		logger.debug("requestUrl is {}", requestUrl);
		int firstQuestionMarkIndex = requestUrl.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			requestUrl = requestUrl.substring(0, firstQuestionMarkIndex);
		}

		if (resourceMap == null) {
			loadResourceDefine();
		}

		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {
			String resURL = ite.next();
//			System.out.println(requestUrl);
			if (resURL.equals(requestUrl)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}