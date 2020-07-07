package com.psl.lhcs.covid.service.user;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.model.login.User;
import com.psl.lhcs.covid.repo.user.UserRepo;

 

@Service
public class UserValidator implements Validator {


    @Autowired
    UserRepo userRepo;
    

    @Value("${message.userDataIsEmpty}")
    private  String userDetailIsEmpty;

    @Value("${valid.contactRegex}")
    private  String contactRegex;

    @Value("${message.userPasswordInvalid}")
    private  String userPasswordInvalid;

    
    @Value("${message.userEmailExists}")
    private  String userEmailExists;
    
    @Value("${message.userContactInvalid}")
    private  String userContactInvalid;
    

    @Override
    public boolean supports(Class<?> cls) {
       
        return User.class.equals(cls);
    }
    @Override
    public void validate(Object obj, Errors error) {
  
       User user = (User) obj;
        
        fieldEmpty(error);
        validateEmail(user,error);
      
        validatePassword(user,error);
        validateContact(user,error);
  
    }
 
    
    public String getErrorMessage(BindingResult errors) {
        
        StringBuilder errorMsg=new StringBuilder();
        for (ObjectError object : errors.getAllErrors()) {
            if(object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                errorMsg.append(fieldError.getCode()).append(" ; ");
            }
            
            else if(object instanceof ObjectError) {
                ObjectError objectError =  object;

 

                errorMsg.append(objectError.getCode()).append(" ; ");
            }
        }
        
        
        return errorMsg.toString();
        
    }
    
    public  void validateEmail(User user,Errors error) {
           if (userRepo.findByEmail(user.getEmail())!=null) {
                error.rejectValue(ApplicationConstants.EMAIL, userEmailExists);
            }
    }
    
    public  void fieldEmpty(Errors error) {
         ValidationUtils.rejectIfEmptyOrWhitespace(error,ApplicationConstants.FIRSTNAME, userDetailIsEmpty);
            ValidationUtils.rejectIfEmptyOrWhitespace(error,ApplicationConstants.LASTNAME , userDetailIsEmpty);
            ValidationUtils.rejectIfEmptyOrWhitespace(error, ApplicationConstants.CONTACT , userDetailIsEmpty);
            ValidationUtils.rejectIfEmptyOrWhitespace(error, ApplicationConstants.EMAIL , userDetailIsEmpty);
            ValidationUtils.rejectIfEmptyOrWhitespace(error, ApplicationConstants.PASSWORD , userDetailIsEmpty);

 

    }
    
    public  void validatePassword(User user,Errors error) {
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            error.rejectValue(ApplicationConstants.PASSWORD, userPasswordInvalid);

 

        }
    }
    public void validateContact(User user,Errors error) {
        if (!user.getContact().matches(contactRegex)) {
            error.rejectValue(ApplicationConstants.CONTACT, userContactInvalid);

 

        }
    }
    
}