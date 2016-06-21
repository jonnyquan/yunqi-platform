package com.yunqi.apis.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.RoleApi;
import com.yunqi.apis.user.api.dto.RoleDto;
import com.yunqi.apis.user.domain.Role;
import com.yunqi.apis.user.service.RoleService;
import com.yunqi.common.view.dto.ContentParam;
import com.yunqi.core.view.BaseController;

@RestController
public class RoleController extends BaseController implements RoleApi{

	@Autowired
	private RoleService service;
	
//	public static Role dtoToDomain(RoleDto dto){
//		if(dto==null) return null;
//		return null;
//	}
	
	public static RoleDto domainToDto(Role domain){
		if(domain==null) return null;
		RoleDto dto = new RoleDto();
		dto.setCode(domain.getCode());
		dto.setName(domain.getName());
		dto.setRemark(domain.getRemark());
		if(domain.getType()!=null) dto.setType(domain.getType().name());
		dto.setValue(domain.getValue());
		return dto;
	}

	@Override
	public List<RoleDto> findRoleBySource(@ContentParam(name="id") String id) {
		ObjectId oId = new ObjectId(id);
		List<Role> rs = service.findRoleBySource(oId);
		List<RoleDto> rds = null;
		if(rs!=null){
			rds = new ArrayList<>();
			for(Role r : rs){
				RoleDto dto = RoleController.domainToDto(r);
				rds.add(dto);
			}
		}
		return rds;
		
	}
	
}
