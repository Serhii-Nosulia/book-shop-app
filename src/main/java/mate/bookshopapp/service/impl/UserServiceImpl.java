package mate.bookshopapp.service.impl;

import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.user.UserRegistrationRequestDto;
import mate.bookshopapp.dto.user.UserResponseDto;
import mate.bookshopapp.exception.RegistrationException;
import mate.bookshopapp.mapper.UserMapper;
import mate.bookshopapp.model.Role;
import mate.bookshopapp.model.ShoppingCart;
import mate.bookshopapp.model.User;
import mate.bookshopapp.repository.role.RoleRepository;
import mate.bookshopapp.repository.shopping.cart.ShoppingCartRepository;
import mate.bookshopapp.repository.user.UserRepository;
import mate.bookshopapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("Couldn't register user. "
                    + "Try another email or password");
        }
        User user = new User();
        user.setEmail(requestDto.email());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.getRoles().add(roleRepository.findByName(Role.RoleName.ROLE_USER));
        user.setFirstName(requestDto.firstName());
        user.setLastName(requestDto.lastName());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        return userMapper.toDto(userRepository.save(user));
    }
}
