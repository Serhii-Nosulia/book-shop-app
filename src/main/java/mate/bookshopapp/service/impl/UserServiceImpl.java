package mate.bookshopapp.service.impl;

import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.user.UserRegistrationRequestDto;
import mate.bookshopapp.dto.user.UserResponseDto;
import mate.bookshopapp.exception.RegistrationException;
import mate.bookshopapp.mapper.UserMapper;
import mate.bookshopapp.model.User;
import mate.bookshopapp.repository.user.UserRepository;
import mate.bookshopapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("Couldn't register user. "
                    + "Try another email or password");
        }
        User user = new User();
        user.setEmail(requestDto.email());
        user.setPassword(requestDto.password());
        user.setFirstName(requestDto.firstName());
        user.setLastName(requestDto.lastName());
        return userMapper.toDto(userRepository.save(user));
    }
}
