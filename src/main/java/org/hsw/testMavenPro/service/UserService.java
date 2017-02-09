package org.hsw.testMavenPro.service;

import java.util.List;

import org.hsw.testMavenPro.domain.User;
import org.hsw.testMavenPro.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	public boolean createUser(UserEntity userEntity);
	
	public UserEntity selectUserByUserName(String userName);
	
	public List<User> selectUsers(int start,int pageSize);
	
	public User selectUserById(String userId);
	
	public boolean updateUser(UserEntity userEntity,MultipartFile file);
	
	public boolean deleteByUserId(String userId);
	
	public int totalCountUsers();
	
	public boolean deleteUserImage(String userId,String imageUrl,String imageName);
}
