package com.yunqi.apis.user.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.earven.rest.dto.ContentParam;
import com.yunqi.apis.user.api.dto.RoleDto;

@RequestMapping("/role")
public interface RoleApi{

	@RequestMapping(path="/findRoleBySource", method = RequestMethod.POST)
	public List<RoleDto> findRoleBySource(@ContentParam(name = "id") String id);
	
}
