package com.yunqi.apis.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.ResourceApi;
import com.yunqi.apis.user.api.dto.ResourceDto;
import com.yunqi.apis.user.domain.Resource;
import com.yunqi.apis.user.service.ResourceService;

@RestController
public class ResourceController implements ResourceApi{

	@Autowired
	private ResourceService service;
	
//	public static Resource dtoToDomain(ResourceDto dto){
//		if(dto==null) return null;
//		return null;
//	}
//	
	public static ResourceDto domainToDto(Resource domain){
		if(domain==null) return null;
		ResourceDto dto = new ResourceDto();
		dto.setCode(domain.getCode());
		dto.setId(domain.getId().toString());
		dto.setName(domain.getName());
		dto.setPermission(domain.getPermission());
		dto.setRemark(domain.getRemark());
		if(domain.getType()!=null) dto.setType(domain.getType().name());
		return dto;
	}

	@Override
	public List<ResourceDto> findAll() {
		List<Resource> rs = service.findAll();
		List<ResourceDto> rds = null;
		if(rs!=null){
			rds = new ArrayList<>();
			for(Resource r : rs){
				ResourceDto dto = ResourceController.domainToDto(r);
				rds.add(dto);
			}
		}
		return rds;
	}
	
}
