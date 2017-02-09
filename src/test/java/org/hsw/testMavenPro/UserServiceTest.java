package org.hsw.testMavenPro;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hsw.testMavenPro.entity.UserEntity;
import org.hsw.testMavenPro.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml",  
        "classpath:spring-mybatis.xml" })
public class UserServiceTest {
	private static final Logger LOGGER = Logger  
            .getLogger(UserServiceTest.class);  
  
    @Autowired  
    private UserService userService; 
    
    @Test
    @Ignore
    public void test_createUser(){
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUserName("000070");
    	userEntity.setPassword("qqqqqq");
    	Date date = new Date();
    	userEntity.setCreateTime(date);
    	userEntity.setLastUpdateTime(date);
    	boolean flag = userService.createUser(userEntity);
    	System.out.println(flag);
    }
    
    @Test
//    @Ignore
    public void test_selectUserByUserName(){
    	UserEntity userEntity = userService.selectUserByUserName("000080");
    	System.out.println(userEntity.getUserName()+" -------------- "+userEntity.getPassword());
    	LOGGER.error(userEntity.getUserName()+" -------------- "+userEntity.getPassword());
    }
}
