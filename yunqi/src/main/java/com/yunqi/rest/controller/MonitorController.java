package com.yunqi.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor")
public class MonitorController{
	
    @RequestMapping("/service")
    public String service(HttpServletRequest request, HttpServletResponse response, Model model) {
    	return "/monitor/service";
    }
    
    @RequestMapping("/controller")
    public String controller(HttpServletRequest request, HttpServletResponse response, Model model) {
    	return "/monitor/controller";
    }
    
}
