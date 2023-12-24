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
        Object firstFieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object secondFieldValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if (firstFieldValue != null) {
            return firstFieldValue.equals(secondFieldValue);
        } else {
            return secondFieldValue == null;
        }
    }
}
