package com.yunqi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//public class TestSupport extends AbstractJUnit4SpringContextTests {
//	
//	public final Logger logger = LoggerFactory.getLogger(getClass());
//	
//}

//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//public class TestSupport extends AbstractTransactionalJUnit4SpringContextTests {
//	
//	public final Logger logger = LoggerFactory.getLogger(getClass());
//	
//}

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSupport{
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Test
    public void test() {
    	logger.debug("test");
    }
	
}