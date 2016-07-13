package com.yunqi.apis.user.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RestController;

import com.yunqi.apis.user.api.UserApi;
import com.yunqi.apis.user.api.dto.ResourceDto;
import com.yunqi.apis.user.api.dto.RoleDto;
import com.yunqi.apis.user.api.dto.UserDto;
import com.yunqi.apis.user.domain.Resource;
import com.yunqi.apis.user.domain.Role;
import com.yunqi.apis.user.domain.User;

@RestController
public class UserController implements UserApi{
	
//	public static User dtoToDomain(UserDto dto){
//		if(dto==null) return null;
//		return null;
//	}
	
	public static UserDto domainToDto(User domain){
		if(domain==null) return null;
		UserDto dto = new UserDto();
		dto.setEmail(domain.getEmail());
		dto.setLastLoginIp(domain.getLastLoginIp());
		dto.setLastLoginTime(domain.getLastLoginTime());
		dto.setName(domain.getName());
		
		Set<Resource> resourceS = domain.getResource();
		if(resourceS!=null && resourceS.size()>0){
			Set<ResourceDto> resourceDtoS = new HashSet<>();
			for(Resource r : resourceS){
				ResourceDto resourceDto = ResourceController.domainToDto(r);
				resourceDtoS.add(resourceDto);
			}
			dto.setResource(resourceDtoS);
		}
		
		Set<Role> roleS = domain.getRoles();
		if(roleS!=null && roleS.size()>0){
			Set<RoleDto> roleDtoS = new HashSet<>();
			for(Role r : roleS){
				RoleDto roleDto = RoleController.domainToDto(r);
				roleDtoS.add(roleDto);
			}
			dto.setRoles(roleDtoS);
		}
		
		if(domain.getState()!=null) dto.setState(domain.getState().name());
		dto.setTel(domain.getTel());
		return dto;
	}

}