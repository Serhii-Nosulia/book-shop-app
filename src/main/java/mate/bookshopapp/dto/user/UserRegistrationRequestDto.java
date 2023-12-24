package mate.bookshopapp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import mate.bookshopapp.validation.field.FieldMatch;
import mate.bookshopapp.validation.password.Password;

@FieldMatch(field = "password",
        fieldMach = "repeatPassword",
        message = "Password and repeatPassword don't Match")
public record UserRegistrationRequestDto(@Email String email,
                                         @Password String password,
                                         String repeatPassword,
                                         @NotBlank String firstName,
                                         @NotBlank String lastName) {
}
