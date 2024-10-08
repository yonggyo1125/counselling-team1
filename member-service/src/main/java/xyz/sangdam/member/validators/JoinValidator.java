package xyz.sangdam.member.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.ValidationUtils;
import xyz.sangdam.global.validators.MobileValidator;
import xyz.sangdam.global.validators.PasswordValidator;
import xyz.sangdam.member.constants.UserType;
import xyz.sangdam.member.controllers.RequestJoin;
import xyz.sangdam.member.repositories.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, PasswordValidator, MobileValidator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        /**
         * 1. 이미 가입된 회원인지 체크
         * 2. 비밀번호, 비밀번호 확인 일치 여부
         * 3. 비밀번호 복잡성 체크
         * 4. 휴대전화번호 형식 체크
         */

        RequestJoin form = (RequestJoin) target;
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String mobile = form.getMobile();

        // 1. 이미 가입된 회원인지 체크
        if (memberRepository.exists(email)) {
            errors.rejectValue("email", "Duplicated");
        }

        //2. 비밀번호, 비밀번호 확인 일치 여부
        if (!password.equals(confirmPassword)) {
            errors.rejectValue("confirmPassword", "Mismatch.password");
        }

        // 3. 비밀번호 복잡성 체크 - 알파벳 대소문자 각각 1개 이상, 숫자 1개 이상, 특수문자 1개 이상
        if (!alphaCheck(password, false) || !numberCheck(password) || !specialCharsCheck(password)) {
            errors.rejectValue("password", "Complexity");
        }

        // 4. 휴대전화번호 형식 체크
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