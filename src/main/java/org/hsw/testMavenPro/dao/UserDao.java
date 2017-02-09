package org.hsw.testMavenPro.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.hsw.testMavenPro.entity.UserEntity;
import org.springframework.stereotype.Service;
@Service
public interface UserDao {
	public boolean createUser(UserEntity userEntity);
	
	public UserEntity selectUserByUserName(String userName);
	
	public List<UserEntity> selectUsers(RowBounds rowBounds);
	
	public UserEntity selectUserById(String userId);
	
	public boolean updateUser(UserEntity userEntity);
	
	public boolean deleteUserById(String userId);
	
	public int totalCountUsers();
}
