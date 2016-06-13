package com.yunqi.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yunqi.Application;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestSupport extends AbstractJUnit4SpringContextTests {
//public class TestSupport extends AbstractTransactionalJUnit4SpringContextTests {
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
}
