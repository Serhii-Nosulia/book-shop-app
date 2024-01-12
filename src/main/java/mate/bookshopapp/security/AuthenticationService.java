package mate.bookshopapp.security;

import lombok.RequiredArgsConstructor;
import mate.bookshopapp.dto.user.UserLoginRequestDto;
import mate.bookshopapp.dto.user.UserLoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        final Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                requestDto.email(),
                                requestDto.password())
                );
        return new UserLoginResponseDto(jwtUtil.generateToken(authentication.getName()));
    }
}
