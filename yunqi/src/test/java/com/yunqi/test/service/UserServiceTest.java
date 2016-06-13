package com.yunqi.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.model.User;
import com.yunqi.service.UserService;
import com.yunqi.test.TestSupport;

public class UserServiceTest extends TestSupport{

	@Autowired
	private UserService service;

	@Test
	public void testCRUD() {

		//CREATE
		User o = new User();
		o.setEmail("admin@163.com");
		service.save(o);
		Assert.assertNotNull(o.getId());

		//READ
		o = service.findById(o.getId());
		Assert.assertNotNull(o.getId());

		//UPDATE
		o.setTel("123");
		service.save(o);
		o = service.findById(o.getId());
		Assert.assertTrue(o.getTel().equals("123"));

		//DELETE
		service.delete(o.getId());
		o = service.findById(o.getId());
		Assert.assertNull(o);

	}

}
