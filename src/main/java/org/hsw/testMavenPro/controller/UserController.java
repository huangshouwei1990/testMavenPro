package org.hsw.testMavenPro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hsw.testMavenPro.domain.ResponseCommon;
import org.hsw.testMavenPro.domain.User;
import org.hsw.testMavenPro.entity.UserEntity;
import org.hsw.testMavenPro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@Controller  
@RequestMapping("/user")
public class UserController {
	@Autowired  
    private UserService userService;  
      
    @RequestMapping("/userDetail/{userName}")  
    public String showUserInfo(ModelMap modelMap, @PathVariable String userName){  
        UserEntity userEntity = userService.selectUserByUserName(userName); 
        modelMap.addAttribute("userEntity", userEntity);  
        return "/user/userDetail";  
    }
    
    @RequestMapping("/userList/{currentPage}/{pageSize}")
    public String showUserList(ModelMap modelMap,
    		@PathVariable int currentPage,
    		@PathVariable int pageSize){
    	List<User> userList = userService.selectUsers(currentPage,pageSize);
    	modelMap.addAttribute("userList",userList);
    	modelMap.addAttribute("currentPage", currentPage);
    	modelMap.addAttribute("pageSize", pageSize);
    	int totalCount = userService.totalCountUsers();
    	int totalPage = totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize+1);
    	modelMap.addAttribute("totalPage", totalPage);
    	
    	List<Integer> pageNumbers = new ArrayList<Integer>();
    	if(currentPage-2>0){
    		pageNumbers.add(currentPage-2);
    	}
		if(currentPage-1>0){
			pageNumbers.add(currentPage-1);
		}
		pageNumbers.add(currentPage);
		if(currentPage+1<=totalPage){
			pageNumbers.add(currentPage+1);
		}
		if(currentPage+2<=totalPage){
			pageNumbers.add(currentPage+2);
		}
		modelMap.addAttribute("pageNumbers", pageNumbers);
    	return "/user/userList";
    }
    
    @RequestMapping(value="/updateUser",method = RequestMethod.POST)
    public String updateUser(ModelMap model,
    		UserEntity userEntity,
    		@RequestParam(value = "imageFile", required = false) MultipartFile imageFile){
    	boolean flag = userService.updateUser(userEntity,imageFile);
    	if(flag){
    		model.addAttribute("message","修改成功！");
    	}else{
    		model.addAttribute("message", "修改失败！");
    	}
    	model.addAttribute("flag",flag);
    	return "redirect:/user/userList/1/10.html";
    }
    
    @RequestMapping(value="/json/userById/{userId}",method=RequestMethod.GET,consumes="application/json")
    @ResponseBody
    public Object selectUserById(ModelMap modelMap, @PathVariable String userId){
    	User user = userService.selectUserById(userId);
    	return user;
    }
      
    @RequestMapping(value="/json/userList",method=RequestMethod.GET,consumes="application/json")
    @ResponseBody
    public Object showUserInfos(){  
        List<User> userList = userService.selectUsers(1,-1);
        return userList;  
    }
    
    @RequestMapping(value="/deleteUserById/{userId}",method = RequestMethod.GET)
    public String deleteUserById(ModelMap model, @PathVariable String userId){
    	boolean flag = userService.deleteByUserId(userId);
    	if(flag){
    		model.addAttribute("message","删除成功！");
    	}else{
    		model.addAttribute("message", "删除失败！");
    	}
    	model.addAttribute("flag",flag);
    	return "redirect:/user/userList/1/10.html";
    }
    
    @RequestMapping(value="/json/deleteUserImage",method = RequestMethod.POST,consumes="application/json")
    @ResponseBody
    public Object deleteUserImage(ModelMap modelMap,
    		@RequestParam Map<String, String> params){
    	ResponseCommon responseCommon = new ResponseCommon();
//    	boolean flag = userService.deleteUserImage(userId, imageUrl, imageName);
//    	if(flag){
//    		responseCommon.setMessage("删除成功！");
//    	}else{
//    		responseCommon.setMessage("删除失败！");
//    	}
//    	responseCommon.setFlag(flag);
    	return responseCommon;
    }
}
