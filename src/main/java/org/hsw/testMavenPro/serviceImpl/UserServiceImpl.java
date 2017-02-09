package org.hsw.testMavenPro.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.hsw.testMavenPro.dao.UserDao;
import org.hsw.testMavenPro.domain.Image;
import org.hsw.testMavenPro.domain.User;
import org.hsw.testMavenPro.entity.UserEntity;
import org.hsw.testMavenPro.service.UserService;
import org.hsw.testMavenPro.util.CommonMethodUtil;
import org.hsw.testMavenPro.util.FileCommonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public boolean createUser(UserEntity userEntity) {
		boolean flag = false;
		if(userEntity!=null){
			String userName = userEntity.getUserName();
			//必须为邮箱
			if(!CommonMethodUtil.ifEmail(userName)){
				return false;
			}
			//用户名不能重复
			UserEntity user = userDao.selectUserByUserName(userName);
			if(user != null){
				return false;
			}
			
			String password = userEntity.getPassword();
			//密码不能为空
			if(StringUtils.isEmpty(password)){
				return false;
			}
			//md5加密密码
			password = CommonMethodUtil.encodeByMd5(password);
			userEntity.setPassword(password);
			
			Date date = new Date();
			userEntity.setCreateTime(date);
			userEntity.setLastUpdateTime(date);
			flag = userDao.createUser(userEntity);
		}
		return flag;
	}

	public UserEntity selectUserByUserName(String userName) {
		UserEntity userEntity = userDao.selectUserByUserName(userName);
		return userEntity;
	}
	
	public List<User> selectUsers(int currentPage,int pageSize){
		int start = (currentPage -1)*pageSize;
		RowBounds rowBounds = new RowBounds(start, pageSize);
		List<UserEntity> userEntityList = userDao.selectUsers(rowBounds);
		List<User> users = new ArrayList<User>();
		for(UserEntity userEntity:userEntityList){
			User user = new User();
			user.setId(userEntity.getId());
			user.setCreateTime(userEntity.getCreateTime());
			user.setLastUpdateTime(userEntity.getLastUpdateTime());
			user.setUserName(userEntity.getUserName());
			user.setPassword(userEntity.getPassword());
			List<Image> imageList = new ArrayList<Image>();
			String images = userEntity.getImages();
			if(StringUtils.isNotEmpty(images)){
				images = images.trim();
				images = images.substring(1, images.length()-1);
				String[] imageArray = images.split(",");
				for(String imageItem:imageArray){
					String[] imageDetail = imageItem.split("\\+");
					String imageName = imageDetail[0];
					String imageUrl = imageDetail[1];
					Image image = new Image();
					image.setImageName(imageName);
					image.setImageUrl(imageUrl);
					imageList.add(image);
				}
			}
			user.setImages(imageList);
			users.add(user);
		}
		return users;
	}

	public User selectUserById(String userId) {
		UserEntity userEntity = userDao.selectUserById(userId);
		User user = new User();
		user.setId(userEntity.getId());
		user.setCreateTime(userEntity.getCreateTime());
		user.setLastUpdateTime(userEntity.getLastUpdateTime());
		user.setUserName(userEntity.getUserName());
		user.setPassword(userEntity.getPassword());
		List<Image> imageList = new ArrayList<Image>();
		String images = userEntity.getImages();
		if(StringUtils.isNotEmpty(images)){
			images = images.trim();
			images = images.substring(1, images.length()-1);
			String[] imageArray = images.split(",");
			for(String imageItem:imageArray){
				String[] imageDetail = imageItem.split("\\+");
				String imageName = imageDetail[0];
				String imageUrl = imageDetail[1];
				Image image = new Image();
				image.setImageName(imageName);
				image.setImageUrl(imageUrl);
				imageList.add(image);
			}
		}
		user.setImages(imageList);
		return user;
	}

	public boolean updateUser(UserEntity userEntity,MultipartFile imageFile) {
		boolean flag = false;
		String userId = userEntity.getId();
		String userName = userEntity.getUserName();
		//必须为邮箱
		if(!CommonMethodUtil.ifEmail(userName)){
			return false;
		}
		
		UserEntity user = userDao.selectUserById(userId);
		if(user!=null){
			user.setUserName(userName);
			user.setLastUpdateTime(new Date());
			
			//上传图片
			FileCommonBean fileCommonBean = CommonMethodUtil.uploadFile(imageFile);
			if(fileCommonBean!=null){
				String imageName = fileCommonBean.getFileName();
				String imageUrl = fileCommonBean.getFileUrl();
				String image = imageName+"+"+imageUrl;
				
				String oldImage = user.getImages();
				if(StringUtils.isEmpty(oldImage)){
					image = ","+image+",";
				}else{
					image = oldImage+image+",";
				}
				user.setImages(image);
			}
			flag = userDao.updateUser(user);
		}else{
			flag = false;
		}
		return flag;
	}

	public boolean deleteByUserId(String userId) {
		boolean flag = userDao.deleteUserById(userId);
		return flag;
	}

	public int totalCountUsers() {
		int totalCount = userDao.totalCountUsers();
		return totalCount;
	}

	public boolean deleteUserImage(String userId,String imageUrl,String imageName) {
		boolean flag = CommonMethodUtil.removeFile(imageUrl);
		if(flag){
			UserEntity userEntity = userDao.selectUserById(userId);
			String images = userEntity.getImages();
			if(StringUtils.isNotEmpty(images)){
				String oldChar = imageName+"+"+imageUrl+",";
				images = images.replace(oldChar, "");
				userEntity.setImages(images);
				return userDao.updateUser(userEntity);
			}
		}
		return false;
	}
}
