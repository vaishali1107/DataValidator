package com.psl.lhcs.covid.service.user;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.model.login.User;
import com.psl.lhcs.covid.repo.user.UserRepo;
import com.psl.lhcs.covid.response.Response;

 

@Service
public class UserService {

 

    @Autowired
    UserValidator userValidator;

 

    @Autowired
    UserRepo userRepo;

 


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

 

    @Value("${message.invalidContact}")
    String invalidContact;

 


    @Value("${message.userLoginSuccess}")
    private String loginSuccess;

 

 

    @Value("${message.userLoginFailed}")
    private String loginFailed;

 

    @Value("${message.password}")
    String oldPassword;

 

    @Value("${message.passwordChanged}")
    String passwordChanged;

 

    @Value("${message.invalidEmail}")
    String invalidEmail;

 

    @Value("${message.accessLevelConflict}")
    private  String accessLevelConflict;

 

    @Value("${message.userRegistrationSuccess}")
    private String registrationSuccess;

 


    public boolean isUserAlreadyPresent(User user) {

 

        User userExist =userRepo.findByEmail(user.getEmail());

 

       return (userExist!=null);

    }

 

    public Response<String> register(User userDetail, BindingResult errors){
        validate(userDetail, errors);
        if (errors.hasErrors()) {

 

            Response<String> response = new Response<>();
            response.setMessage(getErrorMessage(errors));
            response.setCode(406);
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            return response;
        }
        userDetail.setPassword(passwordEncoder.encode(userDetail.getPassword()));

        userRepo.save(userDetail);

        Response<String> response = new Response<>();
        response.setMessage(registrationSuccess);
        response.setCode(200);
        response.setStatus(HttpStatus.OK);
        return response;
    }

 

 

    public Response<String> isUserValid(String email,String password)
    {
        Response<String> response = new Response<>();
        User userValid = userRepo.findByEmail(email);
        if((userValid!=null && passwordEncoder.matches(password, userValid.getPassword()))) {
            response.setMessage(loginSuccess);
            response.setCode(200);
            response.setStatus(HttpStatus.OK);
            return response;
        }
        response.setMessage(loginFailed);
        response.setCode(401);
        response.setStatus(HttpStatus.UNAUTHORIZED);
        return response;

 

    }

 

    public Response<String> resetPassword(String email,String oldPassword,String newPassword) {
        Optional<User> optional = Optional.ofNullable(userRepo.findByEmail(email));
        Response<String> response = new Response<>();
        if (!optional.isPresent()) {
            response.setMessage(invalidEmail);
            response.setCode(401);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return response;

 

        }

 

        User user=optional.get();

 

        if( passwordEncoder.matches(oldPassword, user.getPassword()) ) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepo.save(user);
            response.setMessage(passwordChanged);
            response.setCode(200);
            response.setStatus(HttpStatus.OK);
            return response;

 

        }
        else
            response.setMessage(this.oldPassword);
        response.setCode(401);
        response.setStatus(HttpStatus.UNAUTHORIZED);
        return response;

 


    }

 

    public Response<String> forgetPassword(String email,String newPassword,String contact)
    {
        Optional<User> emailOptional = Optional.ofNullable(userRepo.findByEmail(email));
        Optional<User> contactOptional = Optional.ofNullable(userRepo.findByContact(contact));
        Response<String> response = new Response<>();
        if (!emailOptional.isPresent()) {
            response.setMessage(invalidEmail);
            response.setCode(401);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return response;
        }
        if (!contactOptional.isPresent()) {
            response.setMessage(invalidContact);
            response.setCode(401);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return response;
        }

 

        User user=emailOptional.get();

 

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        response.setMessage(passwordChanged);
        response.setCode(200);
        response.setStatus(HttpStatus.OK);
        return response;
    }

 

    public void validate(Object obj,Errors errors) {

 


        User user = (User)obj;
        if(StringUtils.hasText(user.getLabName()) && !StringUtils.hasText(user.getHospitalName())) {
            user.setOwner(ApplicationConstants.LAB);
        }
        else if(!StringUtils.hasText(user.getLabName()) && StringUtils.hasText(user.getHospitalName())) {
            user.setOwner(ApplicationConstants.HOSPITAL);
        }
        else {

 

            errors.reject(accessLevelConflict);
        }
        userValidator.validate(obj,errors);
    }

 

    public String getErrorMessage(BindingResult errors) {
        return userValidator.getErrorMessage(errors);
    }
}