package org.hsw.testMavenPro.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hsw.testMavenPro.controller.LoginController;
import org.hsw.testMavenPro.dao.UserDao;
import org.hsw.testMavenPro.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SuppressWarnings("deprecation")
public class LoginService implements UserDetailsService{
	private static final Logger logger = Logger  
            .getLogger(LoginController.class);
	
	@Autowired
	private UserDao userDao;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException , DataAccessException  {
		UserDetails user = null;  
		  
        try {  
  
            // 搜索数据库以匹配用户登录名.  
            // 我们可以通过dao使用JDBC来访问数据库  
            UserEntity userEntity = userDao.selectUserByUserName(username);  
  
            user = new User(userEntity.getUserName(), 
            		userEntity.getPassword().toLowerCase(), 
            		true, true, true, true,  
                    getAuthorities(1));  
  
        } catch (Exception e) {  
            logger.error("Error in retrieving user");  
            throw new UsernameNotFoundException("Error in retrieving user");  
        }  
        return user;  
	}
	
	
	/** 
     * 获得访问角色权限 
     *  
     * @param access 
     * @return 
     */  
    public Collection<GrantedAuthority> getAuthorities(Integer access) {  
  
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);  
  
        // 所有的用户默认拥有ROLE_USER权限  
        logger.debug("所有用户设置ROLE_GUEST权限");  
        authList.add(new GrantedAuthorityImpl("ROLE_GUEST"));  
  
        // 如果参数access为1.则拥有ROLE_ADMIN权限  
        if (access.compareTo(1) == 0) {  
            logger.debug("所有用户设置ROLE_USER权限");  
            authList.add(new GrantedAuthorityImpl("ROLE_USER"));  
        }  
  
        return authList;  
    }  

}
