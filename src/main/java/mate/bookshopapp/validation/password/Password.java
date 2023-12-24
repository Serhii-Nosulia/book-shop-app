package mate.bookshopapp.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Password format is incorrect. "
            + "Password must contain eight characters, "
            + "at least one letter, one number and one special character";
    Class<?>[] groups()default {};
    Class<? extends Payload>[] payload() default {};
}
