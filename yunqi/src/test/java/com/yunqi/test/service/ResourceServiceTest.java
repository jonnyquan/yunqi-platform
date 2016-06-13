package com.yunqi.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.model.Resource;
import com.yunqi.service.ResourceService;
import com.yunqi.test.TestSupport;

public class ResourceServiceTest extends TestSupport{

	@Autowired
	private ResourceService service;

	@Test
	public void testCRUD() {

		//CREATE
		Resource o = new Resource();
		o.setCode("test");
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
	
	@Test
	public void testFindAll() {
		List<Resource> list = service.findAll();
		for (Resource r : list) {
			System.out.println(r.getName());
		}
	}

}
