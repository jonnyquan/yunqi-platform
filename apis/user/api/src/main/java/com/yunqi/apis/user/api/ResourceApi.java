package com.yunqi.apis.user.api;

import java.util.List;

import com.earven.rest.service.RestView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunqi.apis.user.api.dto.ResourceDto;

@RequestMapping("/resource")
public interface ResourceApi{

	@RestView
	@RequestMapping(path="/findAll", method = RequestMethod.POST)
	public List<ResourceDto> findAll();
	
}
