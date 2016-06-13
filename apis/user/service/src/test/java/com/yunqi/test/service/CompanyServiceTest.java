package com.yunqi.test.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.apis.user.domain.Company;
import com.yunqi.apis.user.service.CompanyService;
import com.yunqi.test.TestSupport;

public class CompanyServiceTest extends TestSupport{

	@Autowired
	private CompanyService service;

	@Test
	public void testCRUD() {

		//CREATE
		Company o = new Company();
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
