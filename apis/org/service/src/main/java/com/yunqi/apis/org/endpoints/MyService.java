package com.yunqi.apis.org.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	private final CounterService counterService;

	@Autowired
	public MyService(CounterService counterService) {
		this.counterService = counterService;
	}

	public void exampleMethod() {
		this.counterService.increment("services.system.myservice.invoked");
	}

}
