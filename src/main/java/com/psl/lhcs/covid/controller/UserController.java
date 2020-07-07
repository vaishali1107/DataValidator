package com.psl.lhcs.covid.controller;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.model.login.User;
import com.psl.lhcs.covid.response.Response;
import com.psl.lhcs.covid.service.user.UserService;

 


@RestController
@CrossOrigin(origins ="*")
public class UserController {

 

 

    @Autowired
    UserService userService;

 

 


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

 
    @Value("${message.userRegistrationSuccess}")
    private String registrationSuccess;

 

 

    @Value("${message.userRegistrationFailed}")
    private String registrationFailed;

 

 

    @Value("${message.userDetailInvalid}")
    private String userDetailInvalid;

 

 

    @Value("${message.userAlreadyExists}")
    private String userAlreadyExists;

 

    @Value("${message.passwordChanged}")
    String passwordChanged;
    
    @Value("${message.invalidContact}")
    String invalidContact;

 

    
    @Value("${message.password}")
    String oldPassword;
    

 

    
    @Value("${message.invalidEmail}")
    String invalidEmail;

 


    @PostMapping(value="/register",consumes={"application/json"})
    public Response<String> userRegister(@RequestBody User userDetail,BindingResult errors) {
       
        return userService.register(userDetail, errors);
       
    }
    

 

    @PostMapping(value = "/login",consumes = { "application/json" })
    public Response<String> userLogin(@Valid @RequestBody Map<String,String> data) {

 

        return (userService.isUserValid(data.get(ApplicationConstants.EMAIL),data.get(ApplicationConstants.PASSWORD)));

 

    }

 

    
    @PostMapping(value="/forget")
    public Response<String> forgetPassword(@RequestBody Map<String,String> data)
    {
       
        return userService.forgetPassword(data.get(ApplicationConstants.EMAIL),data.get(ApplicationConstants.NEWPASSWORD),data.get(ApplicationConstants.CONTACT));
        
    }

 

    
    @PostMapping(value="/reset")
    public Response<String> resetPassword(@RequestBody Map<String,String> data)
    {

 

        return userService.resetPassword(data.get(ApplicationConstants.EMAIL),data.get(ApplicationConstants.OLDPASSWORD),data.get(ApplicationConstants.NEWPASSWORD));
    }

 

    

 

 

}