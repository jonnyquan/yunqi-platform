package com.yunqi.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yunqi.apis.user.api.UserApi;

public class HessianTest {

	public static void main(String[] args) {
		
		HessianProxyFactory factory = new HessianProxyFactory();  
		try {
			UserApi hello = (UserApi) factory.create(UserApi.class, "sss");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
}


