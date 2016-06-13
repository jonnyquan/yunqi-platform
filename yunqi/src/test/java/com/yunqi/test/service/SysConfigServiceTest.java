package com.yunqi.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.model.SysConfig;
import com.yunqi.service.SysConfigService;
import com.yunqi.test.TestSupport;

public class SysConfigServiceTest extends TestSupport{

	@Autowired
	private SysConfigService service;

	@Test
	public void testCRUD() {

		//CREATE
		SysConfig o = new SysConfig();
		o.setKey("test");
		o.setValue("test");
		service.save(o);
		Assert.assertNotNull(o.getId());

		//READ
		o = service.findById(o.getId());
		Assert.assertNotNull(o.getId());

		//UPDATE
		o.setValue("test1");
		service.save(o);
		o = service.findById(o.getId());
		Assert.assertTrue(o.getValue().equals("test1"));

		//DELETE
		service.delete(o.getId());
		o = service.findById(o.getId());
		Assert.assertNull(o);

	}

}
