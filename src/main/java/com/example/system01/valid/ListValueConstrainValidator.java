package com.example.system01.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的校验器
 */
public class ListValueConstrainValidator implements ConstraintValidator<ListValue, String> {

    private Set<String> set = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        String[] vals = constraintAnnotation.vals();
        for (String val : vals) {
            set.add(val);
        }
    }

    //判断是否校验成功

    /**
     * @param value                      需要校验的值
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}
