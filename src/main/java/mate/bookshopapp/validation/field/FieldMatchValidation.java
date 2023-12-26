package mate.bookshopapp.validation.field;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidation implements ConstraintValidator<FieldMatch, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMach();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object checkingField = new BeanWrapperImpl(value).getPropertyValue(this.field);
        Object controlField = new BeanWrapperImpl(value).getPropertyValue(this.fieldMatch);

        if (checkingField != null) {
            return checkingField.equals(controlField);
        } else {
            return controlField == null;
        }
    }
}
