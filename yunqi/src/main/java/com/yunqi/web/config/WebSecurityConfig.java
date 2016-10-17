package com.yunqi.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.yunqi.web.config.security.LoginSuccessHandler;
import com.yunqi.web.config.security.SimpleAccessDecisionManager;
import com.yunqi.web.config.security.SimpleFilterInvocationSecurityMetadataSource;
import com.yunqi.web.config.security.SimpleSecurityInterceptor;
import com.yunqi.web.config.security.SimpleUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SimpleUserDetailsService simpleUserDetailsService;
	
	@Autowired
	private SimpleAccessDecisionManager accessDecisionManager;
	
	@Autowired
	private SimpleFilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(simpleUserDetailsService);
	}

	@Override
	@Bean(name="authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "/**/*.jsp");
	}
	
	@Autowired
	public SimpleSecurityInterceptor getSecurityInterceptor(){
		SimpleSecurityInterceptor fsi = new SimpleSecurityInterceptor();
		//用户是否拥有所请求资源的权限
		fsi.setAccessDecisionManager(accessDecisionManager);
		//资源与权限对应关系
		fsi.setSecurityMetadataSource(securityMetadataSource);
		return fsi;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//允许所有用户访问”/”和”/home”
		http.authorizeRequests().antMatchers("/login", "/api/**", "/remoting/**", "/test/**").permitAll()
        //其他地址的访问均需验证权限
		.anyRequest().authenticated().and().formLogin()
		//指定登录页是”/login”
		.loginPage("/login").permitAll()
		//登录成功后可使用loginSuccessHandler()存储用户信息，可选。
		.successHandler(loginSuccessHandler())
		//退出登录后的默认网址是”/login”
		.and().logout().logoutSuccessUrl("/login").permitAll()
		.invalidateHttpSession(true)
		//登录后记住用户，下次自动登录
        //数据库中必须存在名为persistent_logins的表
        //建表语句见code15
		.and().rememberMe().tokenValiditySeconds(1209600)
		.and().csrf().disable();
		
		http.addFilterBefore(getSecurityInterceptor(), FilterSecurityInterceptor.class);
		//指定记住登录信息所使用的数据源
//		.tokenRepository(tokenRepository());//code4
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {	
		//指定密码加密所使用的加密器为passwordEncoder()
		//需要将密码加密后写入数据库 //code13
		auth.userDetailsService(simpleUserDetailsService);
		//.passwordEncoder(passwordEncoder());//code5
		//不删除凭据，以便记住用户
		auth.eraseCredentials(false);	
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	public LoginSuccessHandler loginSuccessHandler(){
		return new LoginSuccessHandler();//code6
	}

}
