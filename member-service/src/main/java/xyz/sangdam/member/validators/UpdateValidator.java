package xyz.sangdam.member.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import xyz.sangdam.global.validators.MobileValidator;
import xyz.sangdam.global.validators.PasswordValidator;
import xyz.sangdam.member.constants.UserType;
import xyz.sangdam.member.controllers.RequestUpdate;

@Component
public class UpdateValidator implements Validator, PasswordValidator, MobileValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestUpdate.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestUpdate form = (RequestUpdate)target;

        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String mobile = form.getMobile();

        // 관리자에서 회원 정보 수정시 이메일 정보 필수
        if (!StringUtils.hasText(email)) {
            errors.rejectValue("email", "NotBlank");
        }

        if (StringUtils.hasText(password)) {
            //1. 비밀번호, 비밀번호 확인 일치 여부
            if (!password.equals(confirmPassword)) {
               errors.rejectValue("confirmPassword", "Mismatch.password");
            }

            // 2. 비밀번호 복잡성 체크 - 알파벳 대소문자 각각 1개 이상, 숫자 1개 이상, 특수문자 1개 이상
            if (!alphaCheck(password, false) || !numberCheck(password) || !specialCharsCheck(password)) {
               errors.rejectValue("password", "Complexity");
            }
        }

        // 3. 휴대전화번호 형식 체크
        if (StringUtils.hasText(mobile) && !mobileCheck(mobile)) {
            errors.rejectValue("mobile", "Mobile");
        }

        UserType userType = UserType.valueOf(form.getUserType());
        // 학생 추가 필수 항목 체크
        if (userType == UserType.STUDENT) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stdntNo", "NotBlank");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "grade", "NotBlank");
        } else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empNo", "NotBlank");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "NotBlank");
        }
    }
}
