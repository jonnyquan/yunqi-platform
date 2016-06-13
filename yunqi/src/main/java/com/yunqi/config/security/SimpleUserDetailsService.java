package com.yunqi.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.yunqi.model.Account;
import com.yunqi.model.Role;
import com.yunqi.service.AccountService;

@Component
public class SimpleUserDetailsService implements UserDetailsService {
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired  //数据库服务类
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
		
		Account account = accountService.findByAccountId(accountId);
		Set<Role> roles = account.getUser().getRoles();
		Collection<GrantedAuthority> auths = new ArrayList<>();
		for(Role r : roles){
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority(r.getName());
			auths.add(auth);
		}

		// 获得用户权限
		boolean enables = true;
		// 账户过期否
		boolean accountNonExpired = true;
		// 证书过期否
		boolean credentialsNonExpired = true;
		// 账户锁定否
		boolean accountNonLocked = true;
		// 封装成spring security的user
		User userdetail = new User(accountId, account.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, auths);
		for (GrantedAuthority s : auths) {
			s.getAuthority();
		}
		logger.debug("[auth] auth is {}", auths.toString());
		return userdetail;
		
	}
	
}
