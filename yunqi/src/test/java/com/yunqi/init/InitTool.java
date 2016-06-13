package com.yunqi.init;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.model.Account;
import com.yunqi.model.Resource;
import com.yunqi.model.Role;
import com.yunqi.model.User;
import com.yunqi.model.enums.ResourceType;
import com.yunqi.model.enums.RoleType;
import com.yunqi.model.enums.UserState;
import com.yunqi.service.AccountService;
import com.yunqi.service.ResourceService;
import com.yunqi.service.RoleService;
import com.yunqi.service.UserService;
import com.yunqi.test.TestSupport;

public class InitTool extends TestSupport{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	private Resource resource1;
	private Resource resource2;
	
	private Role role1;
	private Role role2;
	
	private User user1;
	private User user2;
	
	private Account account1;
	private Account account2;

	@Test
	public void doInit() {
		addResource();
		addRole();
		addUser();
		addAccount();
	}
	
	private void addResource(){
		resource1 = new Resource();
		resource1.setName("用户管理");
		resource1.setPermission("/user/manager");
		resource1.setRemark("用户管理");
		resource1.setType(ResourceType.URL);
		resource1.setCode("usermanager");
		resourceService.save(resource1);
		
		resource2 = new Resource();
		resource2.setName("SERVICE监控");
		resource2.setPermission("/monitor/service");
		resource2.setRemark("SERVICE监控");
		resource2.setType(ResourceType.URL);
		resource2.setCode("/monitor/service");
		resourceService.save(resource2);
	}
	
	private void addRole(){
		
		Set<Resource> rourcesSet = new HashSet<Resource>();
		rourcesSet.add(resource1);
		rourcesSet.add(resource2);
		
		role1 = new Role();
		role1.setCode("admin");
		role1.setName("admin");
		role1.setRemark("管理员");
		role1.setResources(rourcesSet);
		role1.setType(RoleType.ADMIN);
		role1.setValue("/admin");
		roleService.save(role1);
		
		role2 = new Role();
		role2.setCode("test");
		role2.setName("test");
		role2.setRemark("测试");
		role2.setType(RoleType.TEST);
		role2.setValue("/test");
		roleService.save(role2);
		
	}

	private void addUser(){
		
		Set<Resource> rourcesSet = new HashSet<Resource>();
		rourcesSet.add(resource1);
		rourcesSet.add(resource2);
		
		Set<Role> roleAdminSet = new HashSet<Role>();
		roleAdminSet.add(role1);
		
		Set<Role> roleTestSet = new HashSet<Role>();
		roleTestSet.add(role2);
		
		user1 = new User();
		user1.setEmail("admin@163.com");
		user1.setTel("13888888888");
		user1.setState(UserState.ENABLE);
		user1.setResource(rourcesSet);
		user1.setRoles(roleAdminSet);
		userService.save(user1);
		
		user2 = new User();
		user2.setEmail("test@163.com");
		user2.setTel("1399999999");
		user2.setState(UserState.ENABLE);
		user2.setRoles(roleTestSet);
		userService.save(user2);
		
	}
	
	private void addAccount(){
		account1 = new Account();
		account1.setAccountId("admin");
		account1.setPassword("123456");
		account1.setUser(user1);
		accountService.save(account1);
		
		account2 = new Account();
		account2.setAccountId("test");
		account2.setPassword("123456");
		account2.setUser(user2);
		accountService.save(account2);
	}
	
}
