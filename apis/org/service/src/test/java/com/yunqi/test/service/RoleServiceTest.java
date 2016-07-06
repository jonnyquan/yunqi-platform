package com.yunqi.test.service;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunqi.apis.user.domain.Resource;
import com.yunqi.apis.user.domain.Role;
import com.yunqi.apis.user.service.RoleService;
import com.yunqi.test.TestSupport;

public class RoleServiceTest extends TestSupport{

	@Autowired
	private RoleService service;

	@Test
	public void testCRUD() {

		//CREATE
		Role o = new Role();
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
	
//	@Test
	public void testFindAll() {
		List<Role> list = service.findAll();
		for (Role r : list) {
			Set<Resource> rSet = r.getResources();
			for(Resource o : rSet){
				System.out.println(o.getName());
			}
			System.out.println(r.getName());
		}
	}
	
	@Test
	public void testFindRoleBySource() {
		ObjectId id = new ObjectId("569a1eddfef92b30b500ef11");
		List<Role> rs = service.findRoleBySource(id);
		for(Role o : rs){
			System.out.println(o.getName());
		}
	}

}
