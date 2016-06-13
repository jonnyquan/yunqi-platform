package com.yunqi.test.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.model.Employee;
import com.yunqi.service.EmployeeService;
import com.yunqi.test.TestSupport;

public class EmployeeServiceTest extends TestSupport{

	@Autowired
	private EmployeeService service;

	@Test
	public void testCRUD() {

		//CREATE
		Employee o = new Employee();
		o.setBirthday(new Date());
		o.setName("test");
		service.save(o);
		Assert.assertNotNull(o.getId());

		//READ
		o = service.findById(o.getId());
		Assert.assertNotNull(o.getId());

		//UPDATE
		o.setName("test1");
		service.save(o);
		o = service.findById(o.getId());
		Assert.assertTrue(o.getName().equals("test1"));

		//DELETE
		service.delete(o.getId());
		o = service.findById(o.getId());
		Assert.assertNull(o);

	}

}
