package org.hsw.testMavenPro.controller;

import org.apache.log4j.Logger;
import org.hsw.testMavenPro.entity.UserEntity;
import org.hsw.testMavenPro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  
@RequestMapping("/auth")
public class LoginController {
	private static final Logger logger = Logger  
            .getLogger(LoginController.class);
	
	@Autowired  
    private UserService userService;
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)  
    public String getLoginPage(  
            @RequestParam(value = "error", required = false) boolean error,  
            ModelMap model) {  
        logger.debug("Received request to show login page");  
        if (error == true) {  
            // Assign an error message  
            model.put("error","用户名或密码有误!");  
        } else {  
            model.put("error", "");  
        }  
        return "/login/loginpage";  
  
    }  
   
    @RequestMapping(value = "/denied", method = RequestMethod.GET)  
    public String getDeniedPage() {  
        logger.debug("Received request to show denied page");  
        return "/login/deniedpage";  
  
    }
    
    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String registerPage(){
    	return "/login/registerpage";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(ModelMap model,UserEntity userEntity){
    	boolean flag = userService.createUser(userEntity);
    	if(flag){
    		return "forward:/user/userList/1/10.html";
    	}else{
    		model.addAttribute("error", "注册失败");
    		return "/login/registerpage";
    	}
    }
}
