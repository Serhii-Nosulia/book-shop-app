package mate.bookshopapp.service;

import mate.bookshopapp.dto.user.UserRegistrationRequestDto;
import mate.bookshopapp.dto.user.UserResponseDto;
import mate.bookshopapp.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
