package com.yunqi.test.user.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.apis.org.domain.Department;
import com.yunqi.apis.org.service.DepartmentService;
import com.yunqi.test.TestSupport;

public class DepartmentServiceTest extends TestSupport{

	@Autowired
	private DepartmentService service;

	@Test
	public void testCRUD() {

		//CREATE
		Department o = new Department();
		o.setCreateTime(new Date());
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
