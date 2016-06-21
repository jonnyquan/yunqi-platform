package com.yunqi.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.yunqi.apis.user.api.dto.UserDto;

public class Test {

	public static void main(String[] args) {
		List<UserDto> list = new ArrayList<UserDto>();
		Method method;
		try {
			method = list.getClass().getMethod("get",Integer.class);
			Class returnTypeClass = method.getReturnType(); 
			System.out.println("");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
