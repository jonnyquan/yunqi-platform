package com.yunqi.apis.user.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunqi.apis.user.api.dto.RoleDto;
import com.yunqi.common.view.dto.FormModel;

@RequestMapping("/role")
public interface RoleApi{

	@RequestMapping(path="/findRoleBySource", method = RequestMethod.POST)
	public List<RoleDto> findRoleBySource(@FormModel("id") String id);
	
}
