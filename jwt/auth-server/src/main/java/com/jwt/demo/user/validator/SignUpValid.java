package com.jwt.demo.user.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)     //컴파일 이후에도 참조 가능
@Documented
@Constraint(validatedBy = SignUpConstraintValidator.class)
public @interface SignUpValid {

    String message() default "유효성 검사에 실패하였습니다.";
    Class<?>[] groups() default {};                     //특정 그룹만 validate
    Class<? extends Payload>[] payload() default {};    //심각도 정의
}
