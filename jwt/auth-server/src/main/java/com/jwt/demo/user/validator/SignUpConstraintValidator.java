package com.jwt.demo.user.validator;

import com.jwt.demo.user.UserService;
import com.jwt.demo.user.model.User;
import com.jwt.demo.user.model.UserDto;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class SignUpConstraintValidator implements ConstraintValidator<SignUpValid, UserDto.SignUpRequestDto> {

    private final UserService userService;


    @Override
    public void initialize(SignUpValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto.SignUpRequestDto signUpRequestDto, ConstraintValidatorContext context) {

        Optional<User> user = userService.findByEmail(signUpRequestDto.getEmail());

        return !user.isPresent();
    }
}
