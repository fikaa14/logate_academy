package com.academy.demo.validator;

import com.academy.demo.security.dto.UserForRegistrationDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserForRegistrationValidatior implements Validator {

    @Override
    public boolean supports(Class<?> clazz) { return clazz.isAssignableFrom(UserForRegistrationDTO.class); }

    @Override
    public void validate(Object target, Errors errors)
    {
        UserForRegistrationDTO userForRegistrationDTO = (UserForRegistrationDTO) target;

        validateFirstName(errors, userForRegistrationDTO);
        validateLastName(errors, userForRegistrationDTO);
        validateUsername(errors, userForRegistrationDTO);
        validatePassword(errors, userForRegistrationDTO);
    }

    private void validatePassword(Errors errors, UserForRegistrationDTO userForRegistrationDTO)
    {
        if(userForRegistrationDTO.getPassword() == null)
        {
            errors.rejectValue(
                    "password",
                    "password.required",
                    "Password is required!"
            );
            return;
        }

        if(userForRegistrationDTO.getPassword().length() < 8)
        {
            errors.rejectValue(
                    "password",
                    "password.length",
                    "Password has to be longer than 8 charachters!"
            );
            return;
        }

        if(!containsUpperCaseLetter(userForRegistrationDTO.getPassword()))
        {
            errors.rejectValue(
                    "password",
                    "password.content",
                    "Password has to contain one uppercase letter!"
            );
            return;
        }

        if(!containsNumericCharachter(userForRegistrationDTO.getPassword()))
        {
            errors.rejectValue(
                    "password",
                    "password.content",
                    "Password has to contain one digit!"
            );
        }
    }

    private void validateUsername(Errors errors, UserForRegistrationDTO userForRegistrationDTO)
    {
        if(userForRegistrationDTO.getUsername() == null)
        {
            errors.rejectValue(
                    "username",
                    "username.required",
                    "Username is required!"
            );
        }

        if(userForRegistrationDTO.getUsername().length()<3)
        {
            errors.rejectValue(
                    "username",
                    "username.length",
                    "Username has to be longer then 3 charachters!"
            );
        }
    }

    private void validateLastName(Errors errors, UserForRegistrationDTO userForRegistrationDTO)
    {
        if(userForRegistrationDTO.getLastName() == null)
        {
            errors.rejectValue(
                    "lastName",
                    "lastName.required",
                    "Last name is required!"
            );
            return;
        }
        if(userForRegistrationDTO.getLastName().trim().length() < 3)
        {
            errors.rejectValue(
                    "lastName",
                    "lastName.length",
                    "Last name has to be longer than 3 charachters"
            );
            return;
        }
    }

    private void validateFirstName(Errors errors, UserForRegistrationDTO userForRegistrationDTO)
    {
        if(userForRegistrationDTO.getFirstName() == null)
        {
            errors.rejectValue(
                    "firstName",
                    "firstName.required",
                    "First name is required!"
            );
            return;
        }

        if(userForRegistrationDTO.getFirstName().trim().length() < 3)
        {
            errors.rejectValue(
                    "firstName",
                    "firstName.length",
                    "First name has to be longer than 3 charachters"
            );
        }
    }

    private boolean containsUpperCaseLetter(String password)
    {
        for(int i=0;i<password.length();i++)
        {
            if(Character.isUpperCase(password.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }

    private boolean containsNumericCharachter(String password)
    {
        for(int i=0;i<password.length();i++)
        {
            if(Character.isDigit(password.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }
}
