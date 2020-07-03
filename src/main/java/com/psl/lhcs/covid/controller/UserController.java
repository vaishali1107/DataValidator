package com.psl.lhcs.covid.controller;
import java.io.IOException;
import java.util.Map;

 

 


import javax.validation.Valid;

 

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

 

import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.model.excelData.DownloadJsonResponse;
import com.psl.lhcs.covid.model.excelData.FileResponse;
import com.psl.lhcs.covid.model.login.User;
import com.psl.lhcs.covid.model.validate.JsonResponse;
import com.psl.lhcs.covid.repo.data.DataRepository;
import com.psl.lhcs.covid.response.Response;
import com.psl.lhcs.covid.service.data.DataService;

 

import com.psl.lhcs.covid.service.user.UserService;
import com.psl.lhcs.covid.service.user.UserValidator;

 

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

 


@RestController
@CrossOrigin(origins ="*")
public class UserController {

 

 

    @Autowired
    UserService userService;

 

 


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

 

    @Autowired
    private UserValidator userValidator;

 

 


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

 


    @RequestMapping(value="/register",method=RequestMethod.POST,consumes={"application/json"})
    public Response<?> UserRegister(@RequestBody User userDetail,BindingResult errors) {
        
        return userService.register(userDetail, errors);
    }
    

 

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { "application/json" })
    public Response<?> UserLogin(@Valid @RequestBody Map<String,String> data) {

 

        return (userService.isUserValid(data.get(ApplicationConstants.EMAIL),data.get(ApplicationConstants.PASSWORD)));

 

    }

 

    
    @PostMapping(value="/forget")
    public Response<?> forgetPassword(@RequestBody Map<String,String> data)
    {
        //return userService.forgetPassword(data.get(ApplicationConstants.EMAIL),data.get(ApplicationConstants.NEWPASSWORD),data.get(ApplicationConstants.CONTACT));
        return userService.forgetPassword(data.get(ApplicationConstants.EMAIL),data.get(ApplicationConstants.NEWPASSWORD),data.get(ApplicationConstants.CONTACT));
        
    }

 

    
    @PostMapping(value="/reset")
    public Response<?> resetPassword(@RequestBody Map<String,String> data)
    {

 

        return userService.resetPassword(data.get(ApplicationConstants.EMAIL),data.get(ApplicationConstants.OLDPASSWORD),data.get(ApplicationConstants.NEWPASSWORD));
    }

 

    

 

 

}